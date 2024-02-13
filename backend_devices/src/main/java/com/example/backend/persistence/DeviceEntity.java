package com.example.backend.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "devices", schema = "DeviceManagement")
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String status;
    private Integer consumption;
    private Integer clientID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }



    public DeviceEntity(String name, String status, Integer consumption, Integer clientID) {
        this.name = name;
        this.status = status;
        this.consumption = consumption;
        this.clientID=clientID;
    }

    public DeviceEntity() {

    }

}
