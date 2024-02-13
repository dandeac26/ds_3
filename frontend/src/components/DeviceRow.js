import React from "react";
import { useState } from "react";
import "../App.css";

export default function DeviceRow(props) {
  const [id, setId] = useState(props.device.id);
  const [name, setName] = useState(props.device.name);
  const [status, setStatus] = useState(props.device.status);
  const [consumption, setConsumption] = useState(props.device.consumption);
  const [clientID, setClientID] = useState(props.device.clientID);

  if (props.device.viewMode) {
    return (
      <div className="Device">
        <p style={{ margin: 20 }}>{id}</p>
        <p style={{ margin: 20 }}>{name}</p>
        <p style={{ margin: 20 }}>{status}</p>
        <p style={{ margin: 20 }}>{consumption}</p>
        <p style={{ margin: 20 }}>{clientID}</p>
        <button onClick={() => props.deleteDevice(props.device)}>Delete</button>
        <button onClick={() => props.editModeChange(props.device.id)}>
          Edit
        </button>
      </div>
    );
  } else {
    return (
      <div className="Device">
        <div className="NewDevice">
          <p style={{ margin: 20 }}>{id}</p>
          <div className="InputBox">
            <label>name:</label>
            <input
              type="text"
              name="name"
              value={name}
              onChange={(event) => setName(event.target.value)}
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
            <label>consumption:</label>
            <input
              type="text"
              name="consumption"
              value={consumption}
              onChange={(event) => setConsumption(event.target.value)}
            />
          </div>
            <div className="InputBox">
            <label>clientID:</label>
            <input
              type="text"
              name="clientID"
              value={clientID}
              onChange={(event) => setClientID(event.target.value)}
            />
          </div>
        </div>
        <button onClick={() => props.updateDevice(id, name, status, consumption, clientID)}>
          Update
        </button>
        <button onClick={() => props.editModeChange(props.device.id)}>
          Restore
        </button>
      </div>
    );
  }
}
