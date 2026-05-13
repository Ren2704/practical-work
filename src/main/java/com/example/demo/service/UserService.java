package com.example.demo.service;

import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import com.example.model.UserUpdateRequest;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();
    UserResponse findById(Long id);
    UserResponse create(UserCreateRequest userCreateRequest);
    UserResponse update(UserUpdateRequest userUpdateRequest, Long id);
    void delete(Long id);
}
