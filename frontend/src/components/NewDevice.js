import React from "react";
import { useState } from "react";
import "../App.css";

export default function NewDevice(props) {
  const [name, setName] = useState("");
  const [status, setStatus] = useState("");
  const [consumption, setConsumption] = useState("");
  const [clientID, setClientID] = useState("");


  const onSubmit = (e) => {
    e.preventDefault();
    let newDevice = { name: name, status: status, consumption: consumption, clientID: clientID };

    fetch("http://127.0.0.1:8081/create-device", {
      method: "POST",
      body: JSON.stringify(newDevice),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        props.deleteDevice(props.id);
        props.updateNewDevice({
          id: data,
          name: name,
          status: status,
          consumption: consumption,
          clientID: clientID,
          viewMode: true,
        });
      })
      .catch(() => {
        console.log("Somthing failed");
      });
  };

  return (
    <form onSubmit={onSubmit}>
      <div className="NewDevice">
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
        <button type="submit">Submit</button>
        <button onClick={() => props.deleteDevice(props.id)}>Delete</button>
      </div>
    </form>
  );
}
