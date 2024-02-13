package com.example.backend.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity getUserEntityById(Integer id);

    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    UserEntity findByUsername(String username);
}
