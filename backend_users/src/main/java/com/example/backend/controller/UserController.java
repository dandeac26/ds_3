package com.example.backend.controller;

import com.example.backend.jwt.JwtUtil;
import com.example.backend.persistence.LoginRequest;
import com.example.backend.persistence.UserEntity;
import com.example.backend.service.UserService;

import com.example.backend.request.AuthRequest;
import com.example.backend.response.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import com.example.backend.controller.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin()
public class UserController {

    UserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    //@CrossOrigin()
    @GetMapping("/user")
    public UserEntity getUserById(@RequestParam Integer id){
        return userService.getUserById(id);
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/users")
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/create-user")
    public ResponseEntity<Integer> addUser(HttpEntity<String> httpEntity){
        Optional<UserEntity> insertionSuccess = userService.insertNewUser(httpEntity);
        Integer userId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            userId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(userId, status);
    }

    //@CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/update-user")
    public ResponseEntity<Integer> updateUser(@RequestParam Integer id, HttpEntity<String> httpEntity){
        Optional<UserEntity> insertionSuccess = userService.updateUser(id, httpEntity);
        Integer userId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(insertionSuccess.isPresent()){
            userId = insertionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(userId, status);
    }

//@CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete-user")
    public ResponseEntity<Integer> deleteUser(@RequestParam Integer id){
        Optional<UserEntity> deletionSuccess = userService.deleteUser(id);
        Integer userId = null;
        HttpStatus status = HttpStatus.CONFLICT;
        if(deletionSuccess.isPresent()){
            userId = deletionSuccess.get().getId();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(userId, status);
    }

    //@CrossOrigin()
//    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<UserEntity> loginUser(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Optional<UserEntity> user = userService.authenticateUser(username, password);
        UserEntity userEntity = null;
        HttpStatus status = HttpStatus.UNAUTHORIZED; // Default status for unsuccessful login

        if (user.isPresent()) {
            userEntity = user.get();
            status = HttpStatus.OK;
        }

        return new ResponseEntity<>(userEntity, status);
    }

     @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        Optional<UserEntity> optionalUser = userService.authenticate(username, password);

        if (optionalUser.isPresent()) {
            UserEntity authenticatedUser = optionalUser.get();
            String jwt = jwtUtil.generateToken(authenticatedUser.getUsername());
            return ResponseEntity.ok(new JwtResponse(jwt, authenticatedUser.getUsername()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("Invalid credentials"));
        }
    }
}
