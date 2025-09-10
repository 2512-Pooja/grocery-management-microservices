package com.grocery.user_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.grocery.user_service.model.dto.request.CreateUserRequest;
import com.grocery.user_service.model.dto.request.UpdateUserRequest;
import com.grocery.user_service.model.dto.response.UserListResponse;
import com.grocery.user_service.model.dto.response.UserResponse;
import com.grocery.user_service.service.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value ="/api/users")
public class UserController {

    @Autowired UserService userService;

    //------------- create user -------------------//
    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        System.out.println("user request in controller: " + request);
        UserResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //------------- read user -------------------//
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> readUser(@PathVariable String id) {
        UserResponse response = userService.readUser(id);
        return ResponseEntity.ok(response);
    }

    //------------- read all users -------------------//
    @GetMapping
    public ResponseEntity<UserListResponse> readUsers(Pageable pageable) {
        UserListResponse response = userService.readUsers(pageable);
        return ResponseEntity.ok(response);
    }

    //------------- update user -------------------//
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequest request) {
        UserResponse response = userService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    //------------- delete user -------------------//
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }   
}
