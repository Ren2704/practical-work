package com.example.demo.mapper.impl;

import com.example.demo.controller.request.users.UsersCreateRequest;
import com.example.demo.entity.UsersEntity;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.util.PasswordHasher;
import org.springframework.stereotype.Component;


@Component
public class UsersMapperImpl implements UsersMapper {
    @Override
    public <T extends UsersCreateRequest> UsersEntity requestMapToUsers(UsersEntity usersEntity, T request) {
        if (request == null)
            return null;
        usersEntity.setLogin(request.getLogin());
        usersEntity.setPassword(PasswordHasher.hashPassword(request.getPassword()));
        usersEntity.setRole(request.getRole());

        return usersEntity;
    }
}
