import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const UsersPage = () => {
  const [devices, setDevices] = useState([]);
  const navigate = useNavigate();
  

  useEffect(() => {
    if (localStorage.getItem("status") !== 'user') {
      if (localStorage.getItem("status") === 'admin') {
        navigate('/admin');
      }
      navigate('/');
    }
    console.log("test");
    fetch("http://127.0.0.1:8081/devices").then((response) => response.json())
        .then((data) => {
            //console.log(data);
            //Display only the device of the logged in user
            //console.log(data[0].clientID + " "+ localStorage.getItem("clientID"));
            const clientID = localStorage.getItem("clientID");
            console.log("Client ID from localStorage:", clientID);

          const filteredDevices = data.filter((device) => {
             console.log("Device:", device);
              return device.clientID == clientID;
          });

            console.log("Filtered Devices:", filteredDevices);
            //console.log(filteredDevices);
            setDevices(filteredDevices);
        });
  }, []);

  return (
      <div className="UserPage">
      <table id="table1">
          <thead>
          <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Status</th>
              <th>Consumption</th>
              <th>Client ID</th>
          </tr>
          </thead>
          <tbody>
          {devices.map(device => (
              <tr>
                  <td>{device.id}</td>
                  <td>{device.name}</td>
                  <td>{device.status}</td>
                  <td>{device.consumption}</td>
                  <td>{device.clientID}</td>
              </tr>
          ))}
          </tbody>
      </table>
      </div>
  )
  

};

export default UsersPage;