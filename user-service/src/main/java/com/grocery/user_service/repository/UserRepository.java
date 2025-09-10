package com.grocery.user_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grocery.user_service.model.entity.User;

public interface UserRepository extends MongoRepository<User, String> {
    
}
