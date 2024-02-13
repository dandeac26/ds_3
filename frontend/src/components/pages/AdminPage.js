import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../App.css";
import UserRow from "../UserRow";
import NewUser from "../NewUser";
import DeviceRow from "../DeviceRow";
import NewDevice from "../NewDevice";

function AdminPage() {
  const navigate = useNavigate();

  

  // Users
  const [users, setUsers] = useState([]);
  const [newUsers, setNewUsers] = useState([]);
  const [newUserMaxID, setnewUserMaxID] = useState(0);

  // Devices
  const [devices, setDevices] = useState([]);
  const [newDevices, setNewDevices] = useState([]);
  const [newDeviceMaxID, setNewDeviceMaxID] = useState(0);

  useEffect(() => {
    if (localStorage.getItem("status") !== 'admin') {
      if (localStorage.getItem("status") === 'user') {
        navigate('/user');
      }
      navigate('/');
    }
    fetch("http://127.0.0.1:8080/users")
      .then((response) => response.json())
      .then((data) => {
        for (const element of data) {
          element["viewMode"] = true;
        }
        setUsers(data);
      });
    fetch("http://127.0.0.1:8081/devices")
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        for (const element of data) {
          element["viewMode"] = true;
        }
        
        setDevices(data);
      });
  }, []);

  function deleteUser(value) {
    fetch("http://127.0.0.1:8080/delete-user?id=" + value.id, {
      method: "DELETE",
    }).then(() => {
      setUsers((oldValues) => {
        return oldValues.filter((user) => user !== value);
      });
    });
  }

  function deleteDevice(value) {
    fetch("http://127.0.0.1:8081/delete-device?id=" + value.id, {
      method: "DELETE",
    }).then(() => {
      setDevices((oldValues) => {
        return oldValues.filter((device) => device !== value);
      });
    });
  }

  function editModeChangeUser(id) {
    console.log("Changing user edit mode");
    for (const element of users) {
      if (element.id == id) {
        element["viewMode"] = !element["viewMode"];
      }
    }
    setUsers((prevUsers) => [...prevUsers]);
  }

  function editModeChangeDevice(id) {
    console.log("Changing device edit mode");
    for (const element of devices) {
      if (element.id == id) {
        element["viewMode"] = !element["viewMode"];
      }
    }
    setDevices((prevDevices) => [...prevDevices]);
  }

  function updateUser(id, username, email, password, status, deviceID) {
    let userForUpdate = { username: username, email: email, password: password, status: status, deviceID: deviceID };
    fetch("http://127.0.0.1:8080/update-user?id=" + id, {
      method: "PUT",
      body: JSON.stringify(userForUpdate),
    }).then(() => {
      for (const element of users) {
        if (element.id == id) {
          element.username = username;
          element.email = email;
          element.password = password;
          element.status = status;
          editModeChangeUser(id);
          break;
        }
      }
    });
  }

  function updateDevice(id, name, status, consumption, clientID) {
    let deviceForUpdate = { name: name, status: status, consumption: consumption, clientID: clientID };
    fetch("http://127.0.0.1:8081/update-device?id=" + id, {
      method: "PUT",
      body: JSON.stringify(deviceForUpdate),
    }).then(() => {
      for (const element of devices) {
        if (element.id == id) {
          element.name = name;
          element.status = status;
          element.consumption = consumption;
          element.clientID = clientID;
          editModeChangeDevice(id);
          break;
        }
      }
    });
  }

  function addNewUserInput() {
    setNewUsers((newUsers) => [...newUsers, newUserMaxID]);
    setnewUserMaxID(() => newUserMaxID + 1);
  }

  function deleteNewUser(id) {
    setNewUsers((oldValues) => {
      return oldValues.filter((user) => user !== id);
    });
  }

  function updateNewUser(newUser) {
    setUsers([...users, newUser]);
  }

  function addNewDeviceInput() {
    setNewDevices((newDevices) => [...newDevices, newDeviceMaxID]);
    setNewDeviceMaxID(() => newDeviceMaxID + 1);
  }

  function deleteNewDevice(id) {
    setNewDevices((oldValues) => {
      return oldValues.filter((device) => device !== id);
    });
  }

  function updateNewDevice(newDevice) {
    setDevices([...devices, newDevice]);
  }

  // TODO: Move USERS DASHBOARD and DEVICES DASHBOARD in their own components
  return (
    <div className="App">
      <header className="App-header">
        <p>Admin dashboard</p>
      </header>

      {/* USERS DASHBOARD */}
      <div>
        <div className="Users-section">
          <span>Users dashboard</span>
          {users.map((user) => (
            <div key={user.id}>
              <UserRow
                user={user}
                deleteUser={deleteUser}
                editModeChange={editModeChangeUser}
                updateUser={updateUser}
              />
            </div>
          ))}
        </div>
        {newUsers.map((newUserID) => (
          <div key={newUserID}>
            <NewUser
              id={newUserID}
              deleteUser={deleteNewUser}
              updateNewUser={updateNewUser}
            ></NewUser>
          </div>
        ))}
        <div>
          <button onClick={() => addNewUserInput()}>New User</button>
        </div>
      </div>

      {/* DEVICES DASHBOARD */}
      <div>
        <div className="Devices-section">
          <span>Devices dashboard</span>
          {devices.map((device) => (
            <div key={device.id}>
              <DeviceRow
                device={device}
                deleteDevice={deleteDevice}
                editModeChange={editModeChangeDevice}
                updateDevice={updateDevice}
              />
            </div>
          ))}
        </div>
        {newDevices.map((newDeviceID) => (
          <div key={newDeviceID}>
            <NewDevice
              id={newDeviceID}
              deleteDevice={deleteNewDevice}
              updateNewDevice={updateNewDevice}
            ></NewDevice>
          </div>
        ))}
        <div>
          <button onClick={() => addNewDeviceInput()}>New Device</button>
        </div>
      </div>
    </div>
  );
}

export default AdminPage;
