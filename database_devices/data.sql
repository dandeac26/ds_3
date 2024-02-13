CREATE DATABASE DeviceManagement;
USE DeviceManagement;

CREATE TABLE devices(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    consumption INT NOT NULL,
    clientID INT,
    PRIMARY KEY (id)
);

INSERT INTO devices (name, status,consumption, clientID)
VALUES("device1", "active",33, 1);

INSERT INTO devices (name, status,consumption)
VALUES("device2", "inactive",69);



