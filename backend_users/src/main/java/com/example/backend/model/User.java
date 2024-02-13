package com.example.backend.model;

public class User {

    //TODO update DB with SQL for the new fields

    private String username;
    private String email;
    private String password;
    private String status;
    private Integer deviceID;

    public User(String username, String email, String password, String status, Integer deviceID) {
        this.username = username;
        this.email = email;
        this.password=password;
        this.status = status;
        this.deviceID=deviceID;
    }

    public User(String username, String email, String password, String status) {
        this.username = username;
        this.email = email;
        this.password=password;
        this.status = status;

    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Integer deviceID) {
        this.deviceID = deviceID;
    }
}
