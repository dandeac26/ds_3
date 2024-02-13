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

  const clientID = localStorage.getItem("clientID");
  console.log("Client ID from localStorage:", clientID);

// Assuming you have an authentication token stored in localStorage
  const authToken = localStorage.getItem("authToken");
  console.log("Auth Token from localStorage:", authToken);

  fetch("http://localhost:8081/devices", {
      headers: {
          'Authorization': `Bearer ${authToken}`,
          Accept: "application/json",
          "Content-Type": "application/json",
      }
  })
      .then((response) => response.json())
      .then((data) => {
          const filteredDevices = data.filter((device) => {
              return device.clientID == clientID;
          });

          console.log("Filtered Devices:", filteredDevices);
          // console.log(filteredDevices);
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