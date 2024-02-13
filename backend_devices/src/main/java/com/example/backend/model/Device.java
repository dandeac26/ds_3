package com.example.backend.model;

public class Device {



    private String name;
    private String status;
    private Integer consumption;
    private Integer clientID;

    public Device(String name, String status, Integer consumption, Integer clientID) {
        this.name = name;
        this.status = status;
        this.consumption = consumption;
        this.clientID = clientID;
    }

    public Device(String name, String status, Integer consumption) {
        this.name = name;
        this.status = status;
        this.consumption = consumption;
    }

    public Device() {
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

}
