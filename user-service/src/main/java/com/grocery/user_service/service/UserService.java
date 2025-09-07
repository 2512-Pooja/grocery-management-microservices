package com.grocery.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.grocery.user_service.entity.User;
import com.grocery.user_service.exception.UserNotFoundException;
import com.grocery.user_service.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //------------- create user -------------------//
    public User createUser(User user) {
       return userRepository.save(user);
    }

    //------------- read user -------------------//
    public User readUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        return user.get();
    }

    //------------- read all users -------------------//
    public List<User> readUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    //------------- update user -------------------//
    public User updateUser(String id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        } else {
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setUserName(user.getUserName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setPhoneNo(user.getPhoneNo());
            existingUser.setStatus(user.getStatus());
            existingUser.setOrderIds(user.getOrderIds());
            existingUser.setRole(user.getRole());
            userRepository.save(existingUser);
        }
        return existingUser;
    }

    //------------- delete user -------------------//
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found"); 
        }
        userRepository.deleteById(id);
    }
    
}
