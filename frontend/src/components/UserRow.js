import React from "react";
import { useState } from "react";
import "../App.css";

export default function UserRow(props) {
  const [id, setId] = useState(props.user.id);
  const [username, setUsername] = useState(props.user.username);
  const [email, setEmail] = useState(props.user.email);
  const [password, setPassword] = useState(props.user.password);
  const [status, setStatus] = useState(props.user.status);
  const [deviceID, setDeviceID] = useState(props.user.deviceID);

  if (props.user.viewMode) {
    return (
      <div className="User">
        <p style={{ margin: 20 }}>{id}</p>
        <p style={{ margin: 20 }}>{username}</p>
        <p style={{ margin: 20 }}>{email}</p>
        <p style={{ margin: 20 }}>{password}</p>
        <p style={{ margin: 20 }}>{status}</p>
        <p style={{ margin: 20 }}>{status}</p>
        <button onClick={() => props.deleteUser(props.user)}>Delete</button>
        <button onClick={() => props.editModeChange(props.user.id)}>
          Edit
        </button>
      </div>
    );
  } else {
    return (
      <div className="User">
        <div className="NewUser">
          <p style={{ margin: 20 }}>{id}</p>
          <div className="InputBox">
            <label>username:</label>
            <input
              type="text"
              name="username"
              value={username}
              onChange={(event) => setUsername(event.target.value)}
            />
          </div>
          <div className="InputBox">
            <label>email:</label>
            <input
              type="email"
              name="email"
              value={email}
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
              value={status}
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
        </div>
        <button onClick={() => props.updateUser(id, username, email, password, status, deviceID)}>
          Update
        </button>
        <button onClick={() => props.editModeChange(props.user.id)}>
          Restore
        </button>
      </div>
    );
  }
}
