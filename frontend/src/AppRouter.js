// AppRouter.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './components/pages/LoginPage';
import AdminPage from './components/pages/AdminPage';
import UserPage from './components/pages/UserPage';

const AppRouter = () => {
  const [loggedInUser, setLoggedInUser] = React.useState({});

  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser}/>} />
        <Route path="/admin" element={<AdminPage/>} />
        <Route path="/user" element={<UserPage/>} />
      </Routes>
    </Router>
  );
};

export default AppRouter;