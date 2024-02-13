import React from "react";
import { useState } from "react";
import "../App.css";

export default function NewUser(props) {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [status, setStatus] = useState("");
  const [deviceID, setDeviceID] = useState("");

  const onSubmit = (e) => {
    e.preventDefault();
    let newUser = { username: username, email: email, status: status, password: password, deviceID: deviceID };

    fetch("http://127.0.0.1:8080/create-user", {
      method: "POST",
      body: JSON.stringify(newUser),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        props.deleteUser(props.id);
        props.updateNewUser({
          id: data,
          username: username,
          email: email,
          status: status,
          deviceID: deviceID,
          viewMode: true,
        });
      })
      .catch(() => {
        console.log("Somthing failed");
      });
  };

  return (
    <form onSubmit={onSubmit}>
      <div className="NewUser">
        <div className="InputBox">
          <label>username:</label>
          <input
            type="text"
            name="username"
            onChange={(event) => setUsername(event.target.value)}
          />
        </div>
        <div className="InputBox">
          <label>email:</label>
          <input
            type="email"
            name="email"
            onChange={(event) => setEmail(event.target.value)}
          />
        </div>
        <div className="InputBox">
            <label>password:</label>
            <input
              type="text"
              name="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
          </div>
        <div className="InputBox">
          <label>status:</label>
          <input
            type="text"
            name="status"
            onChange={(event) => setStatus(event.target.value)}
          />
        </div>
            <div className="InputBox">
            <label>deviceID:</label>
            <input
              type="text"
              name="deviceID"
              value={deviceID}
              onChange={(event) => setDeviceID(event.target.value)}
            />
          </div>
        <button type="submit">Submit</button>
        <button onClick={() => props.deleteUser(props.id)}>Delete</button>
      </div>
    </form>
  );
}
