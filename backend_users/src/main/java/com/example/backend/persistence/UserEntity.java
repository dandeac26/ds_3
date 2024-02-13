package com.example.backend.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "UserManagement")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String status;
    private Integer deviceID;


    public UserEntity(String username, String email, String password, String status, Integer deviceID) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.password = password;
        this.deviceID = deviceID;
    }

    public UserEntity(String username, String email, String password, String status) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.password = password;
    }

    public UserEntity() {

    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(Integer deviceID) {
        this.deviceID = deviceID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
