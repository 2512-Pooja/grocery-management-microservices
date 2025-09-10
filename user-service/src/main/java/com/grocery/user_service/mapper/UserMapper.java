package com.grocery.user_service.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.grocery.user_service.model.dto.UserDto;
import com.grocery.user_service.model.dto.request.CreateUserRequest;
import com.grocery.user_service.model.dto.request.UpdateUserRequest;
import com.grocery.user_service.model.dto.response.UserListResponse;
import com.grocery.user_service.model.dto.response.UserResponse;
import com.grocery.user_service.model.entity.User;

public class UserMapper {

    // Convert CreateUserRequest to User entity
    public static User toEntity(CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUserName(request.getUserName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNo(request.getPhoneNo());
        user.setRole(request.getRole());
        user.setStatus(request.getStatus() == null || request.getStatus().isBlank() ? "ACTIVE" : request.getStatus());
        user.setOrderIds(List.of()); // Initialize empty list
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return user;
    }

    // Convert UpdateUserRequest to User entity (for partial updates)
    public static void updateEntityFromRequest(UpdateUserRequest request, User user) {
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }
        if (request.getUserName() != null) {
            user.setUserName(request.getUserName());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getPassword() != null) {
            user.setPassword(request.getPassword());
        }
        if (request.getPhoneNo() != null) {
            user.setPhoneNo(request.getPhoneNo());
        }
        if (request.getStatus() != null) {
            user.setStatus(request.getStatus());
        }
        if (request.getRole() != null) {
            user.setRole(request.getRole());
        }
        user.setUpdatedAt(LocalDateTime.now());
    }

    // Convert User entity to UserResponse
    public static UserResponse toResponse(User user) {
        return new UserResponse(
            user.getUserId(),
            user.getFirstName(),
            user.getLastName(),
            user.getUserName(),
            user.getEmail(),
            user.getPhoneNo(),
            user.getStatus(),
            user.getOrderIds(),
            user.getRole(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }

    // Convert User entity to UserDto
    public static UserDto toDto(User user) {
        return UserDto.fromEntity(user);
    }

    // Convert UserDto to UserResponse
    public static UserResponse dtoToResponse(UserDto dto) {
        return new UserResponse(
            dto.getUserId(),
            dto.getFirstName(),
            dto.getLastName(),
            dto.getUserName(),
            dto.getEmail(),
            dto.getPhoneNo(),
            dto.getStatus(),
            dto.getOrderIds(),
            dto.getRole(),
            dto.getCreatedAt(),
            dto.getUpdatedAt()
        );
    }

    // Convert List<User> to UserListResponse
    public static UserListResponse toListResponse(Page<User> userPage) {
        List<UserResponse> userResponses = userPage.getContent()
            .stream()
            .map(UserMapper::toResponse)
            .collect(Collectors.toList());

        return new UserListResponse(
            userResponses,
            (int) userPage.getTotalElements(),
            userPage.getTotalPages(),
            userPage.getNumber(),
            userPage.getSize(),
            userPage.hasNext(),
            userPage.hasPrevious()
        );
    }

    // Convert List<User> to UserListResponse (simple list)
    public static UserListResponse toListResponse(List<User> users, int totalElements, int totalPages, 
                                                 int currentPage, int pageSize, boolean hasNext, boolean hasPrevious) {
        List<UserResponse> userResponses = users.stream()
            .map(UserMapper::toResponse)
            .collect(Collectors.toList());

        return new UserListResponse(
            userResponses,
            totalElements,
            totalPages,
            currentPage,
            pageSize,
            hasNext,
            hasPrevious
        );
    }
}
