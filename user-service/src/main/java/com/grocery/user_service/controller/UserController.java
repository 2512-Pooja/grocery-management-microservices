package com.grocery.user_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.grocery.user_service.entity.User;
import com.grocery.user_service.service.UserService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(value ="/api/users")
public class UserController {

    @Autowired UserService userService;

    //------------- create user -------------------//
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        System.out.println("user in controller "+user);
        return ResponseEntity.ok(userService.createUser(user));
    }

    //------------- read user -------------------//
    @GetMapping("/readUser/{id}")
    public ResponseEntity<User> readUser(@PathVariable String id) {
        return ResponseEntity.ok(userService.readUser(id));
    }

    //------------- read all users -------------------//
    @GetMapping("/readUsers")
    public ResponseEntity<List<User>> readUsers(Pageable pageable) {
         List<User> users = userService.readUsers(pageable);
         return ResponseEntity.ok(users);
    }

    //------------- update user -------------------//
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    //------------- delete user -------------------//
    @ExceptionHandler(RuntimeException.class)
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }   
}
