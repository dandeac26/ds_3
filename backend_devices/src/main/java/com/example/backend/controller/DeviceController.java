package com.example.backend.controller;

import com.example.backend.persistence.DeviceEntity;
import com.example.backend.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DeviceController {

    DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }


    
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/device")
    public DeviceEntity getDeviceById(@RequestParam Integer id){
        return deviceService.getDeviceById(id);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/devices")
    public List<DeviceEntity> getAllDevices(){
        return deviceService.getAllDevices();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("create-device")
    public ResponseEntity<Integer> addDevice(HttpEntity<String> httpEntity){
        Optional<DeviceEntity> insertionSuccess = deviceService.insertNewDevice(httpEntity);
        Integer deviceId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            deviceId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(deviceId, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("update-device")
    public ResponseEntity<Integer> updateDevice(@RequestParam Integer id, HttpEntity<String> httpEntity){
        Optional<DeviceEntity> insertionSuccess = deviceService.updateDevice(id, httpEntity);
        Integer deviceId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            deviceId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(deviceId, status);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("delete-device")
    public ResponseEntity<Integer> deleteDevice(@RequestParam Integer id){
        Optional<DeviceEntity> deletionSuccess = deviceService.deleteDevice(id);
        Integer deviceId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(deletionSuccess.isPresent()){
            deviceId = deletionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(deviceId, status);
    }
}

