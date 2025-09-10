package com.grocery.user_service.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.grocery.user_service.model.entity.User;

public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    @JsonIgnore
    private String password; // Never expose password in DTO
    private String phoneNo;
    private String status;
    private List<String> orderIds;
    private String role;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime updatedAt;

    public UserDto(String userId, String firstName, String lastName, String userName, String email, String password, String phoneNo, String status, List<String> orderIds, String role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phoneNo = phoneNo;
        this.status = status;
        this.orderIds = orderIds;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public String getUserId() {
        return userId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
    public String getStatus() {
        return status;
    }
    public List<String> getOrderIds() {
        return orderIds;
    }
    public String getRole() {
        return role;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setOrderIds(List<String> orderIds) {
        this.orderIds = orderIds;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "UserDto [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", email=" + email + ", password=" + password + ", phoneNo=" + phoneNo + ", status=" + status + ", orderIds=" + orderIds + ", role=" + role + "]";
    }

    public static UserDto fromEntity(User user) {
        return new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(), user.getPassword(), user.getPhoneNo(), user.getStatus(), user.getOrderIds(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }
    
    public User toEntity() {
        User user = new User(userId, firstName, lastName, userName, email, password, phoneNo, status, orderIds, role);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);
        return user;
    }
    public UserDto() {
        super();
    }
    
}
