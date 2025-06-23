package com.example.demo.service;

import com.example.model.UserCreateRequest;
import com.example.model.UserUpdateRequest;
import com.example.demo.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long id);
    UserEntity create(UserCreateRequest usersCreateRequest);
    UserEntity update(UserUpdateRequest usersUpdateRequest, Long id);
    void delete(Long id);
}
