package com.example.backend.service;

import com.example.backend.model.Device;
import com.example.backend.persistence.DeviceEntity;
import com.example.backend.persistence.DeviceRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    DeviceRepository deviceRepository;


    
    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public DeviceEntity getDeviceById(Integer id){
        return deviceRepository.getDeviceEntityById(id);
    }

    public List<DeviceEntity> getAllDevices(){
        return deviceRepository.findAll();
    }

    public Optional<DeviceEntity> insertNewDevice(HttpEntity<String> device){
        Optional<DeviceEntity> insertedDevice = Optional.empty();

        //Json input from body is turned into a device model
        Optional<Device> deviceFromHttpBody = jsonToDeviceModel(device.getBody());

        //TODO Check if data is actually valid
        if(deviceFromHttpBody.isPresent()){
            DeviceEntity newDevice = deviceEntityMapper(deviceFromHttpBody.get());
            DeviceEntity returnedDevice = deviceRepository.save(newDevice);
            insertedDevice = Optional.of(returnedDevice);
        }

        return insertedDevice;
    }

    public Optional<DeviceEntity> updateDevice(Integer id, HttpEntity<String> device) {
        Optional<DeviceEntity> updatedDevice = Optional.empty();
        Optional<DeviceEntity> oldDevice = Optional.ofNullable(deviceRepository.getDeviceEntityById(id));

        if(oldDevice.isEmpty()){
            return updatedDevice;
        }

        Optional<Device> deviceFromHttpBody = jsonToDeviceModel(device.getBody());

        //TODO Check if data is actually valid
        if(deviceFromHttpBody.isPresent()){
            DeviceEntity deviceToBeUpdated = updateDevice(deviceFromHttpBody.get(), oldDevice.get());
            DeviceEntity returnedDevice = deviceRepository.save(deviceToBeUpdated);
            updatedDevice = Optional.of(returnedDevice);
        }
        return updatedDevice;
    }

    private DeviceEntity updateDevice(Device newDeviceInformation, DeviceEntity device){
        if(newDeviceInformation.getName() != null){
            device.setName(newDeviceInformation.getName());
        }

        if(newDeviceInformation.getClientID() != null){
            device.setClientID(newDeviceInformation.getClientID());
        }

        if(newDeviceInformation.getStatus() != null){
            device.setStatus(newDeviceInformation.getStatus());
        }

        if(newDeviceInformation.getConsumption() != null){
            device.setConsumption(newDeviceInformation.getConsumption());
        }

        return device;
    }

    private DeviceEntity deviceEntityMapper(Device device){
        return new DeviceEntity(device.getName(), device.getStatus(), device.getConsumption(), device.getClientID());
    }

    private Optional<Device> jsonToDeviceModel(String jsonDevice){
        ObjectMapper mapper = new ObjectMapper();
        Optional<Device> device = Optional.empty();
        try {
            Device mappedDevice = mapper.readValue(jsonDevice, Device.class);
            device = Optional.of(mappedDevice);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return device;
    }

    public Optional<DeviceEntity> deleteDevice(Integer id) {

        Optional<DeviceEntity> deviceToBeDeleted = deviceRepository.findById(id);

        if(deviceToBeDeleted.isPresent()){
            deviceRepository.deleteById(id);
        }

        return deviceToBeDeleted;
    }

//    public List<Device> findDevicesByUsername(String username) {
//
//
//    }
}
