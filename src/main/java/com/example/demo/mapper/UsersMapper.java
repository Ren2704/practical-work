package com.example.demo.mapper;

import com.example.demo.controller.request.users.UsersCreateRequest;
import com.example.demo.entity.UsersEntity;

public interface UsersMapper {
    <T extends UsersCreateRequest> UsersEntity requestMapToUsers(UsersEntity users, T request);
}
