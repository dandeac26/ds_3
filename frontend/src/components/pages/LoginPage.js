import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const LoginPage = ({loggedInUser, setLoggedInUser}) => {
    const navigate = useNavigate();

    if (loggedInUser?.status === 'admin')
    {
      localStorage.setItem("status",loggedInUser.status);
      navigate('/admin');
    }

    if (loggedInUser?.status === 'user')
    {
      localStorage.setItem("status",loggedInUser.status);
      localStorage.setItem("clientID",loggedInUser.id);
      navigate('/user');
    }



    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');


    function performLogin() {
        const userData = JSON.stringify({ 'username': username, 'password': password });
        console.log(userData);

        fetch("http://localhost:8080/authenticate", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: userData,
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);

                // Assuming the token is present in the 'token' property of the response
                const authToken = data.token;

                // Store the token in local storage
                localStorage.setItem("authToken", authToken);

                // Set the logged-in user state or perform any other actions as needed
                setLoggedInUser(data);
            })
            .catch((error) => {
                console.error('Error during login:', error);
                // Handle the error as needed
            });
        if(localStorage.getItem("status") === "admin")
            navigate('/admin');
        else
            navigate('/user');
    }

  return (
      <div className="User">
        <div className="Login">
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
            <label>password:</label>
            <input
              type="text"
              name="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
          </div>
        </div>
        <button onClick={() => performLogin()}>
          Login
        </button>
      </div>
    );
};

export default LoginPage;