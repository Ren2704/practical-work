package com.example.demo.service;

import com.example.demo.controller.request.users.UsersCreateRequest;
import com.example.demo.controller.request.users.UsersUpdateRequest;
import com.example.demo.entity.UsersEntity;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<UsersEntity> findAll();
    Optional<UsersEntity> findById(Long id);
    UsersEntity create(UsersCreateRequest usersCreateRequest);
    UsersEntity update(UsersUpdateRequest usersUpdateRequest);
    void delete(Long id);
}
