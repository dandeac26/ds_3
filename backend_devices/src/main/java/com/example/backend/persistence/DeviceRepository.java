package com.example.backend.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {

    DeviceEntity getDeviceEntityById(Integer id);
}
