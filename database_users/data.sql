CREATE DATABASE UserManagement;
USE UserManagement;

CREATE TABLE users(
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    deviceID INT,
    PRIMARY KEY (id)
);

INSERT INTO users (username, email,password, status,deviceID)
VALUES("Tudor", "tudor@mail.com","password", "admin", 1);

INSERT INTO users (username, email,password, status)
VALUES("Tudor2", "tudor2@mail.com","password2", "user");
