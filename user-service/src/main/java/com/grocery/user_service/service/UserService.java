package com.grocery.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.grocery.user_service.exception.UserNotFoundException;
import com.grocery.user_service.mapper.UserMapper;
import com.grocery.user_service.model.dto.UserDto;
import com.grocery.user_service.model.dto.request.CreateUserRequest;
import com.grocery.user_service.model.dto.request.UpdateUserRequest;
import com.grocery.user_service.model.dto.response.UserListResponse;
import com.grocery.user_service.model.dto.response.UserResponse;
import com.grocery.user_service.model.entity.User;
import com.grocery.user_service.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //------------- create user -------------------//
    public UserResponse createUser(CreateUserRequest request) {
        User user = UserMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return UserMapper.toResponse(savedUser);
    }

    //------------- read user -------------------//
    public UserResponse readUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        return UserMapper.toResponse(user.get());
    }

    //------------- read all users -------------------//
    public UserListResponse readUsers(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return UserMapper.toListResponse(userPage);
    }

    //------------- update user -------------------//
    public UserResponse updateUser(String id, UpdateUserRequest request) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        
        UserMapper.updateEntityFromRequest(request, existingUser);
        User savedUser = userRepository.save(existingUser);
        return UserMapper.toResponse(savedUser);
    }

    //------------- delete user -------------------//
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " not found"); 
        }
        userRepository.deleteById(id);
    }

    //------------- Additional methods for DTOs -------------------//
    public UserDto getUserDto(String id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
        return UserMapper.toDto(user.get());
    }

    public List<UserDto> getAllUserDtos(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent().stream()
            .map(UserMapper::toDto)
            .toList();
    }
    
}
