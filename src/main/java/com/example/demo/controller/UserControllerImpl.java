package com.example.demo.controller;

import com.example.api.UserControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.UserService;
import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import com.example.model.UserUpdateRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserControllerApi {

    private final UserService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> findAllUsers() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Roles.ADMIN)
    public UserResponse findUserById(Long id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(Roles.ADMIN)
    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        return service.create(userCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Roles.ADMIN)
    public UserResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        return service.update(userUpdateRequest,id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Roles.ADMIN)
    public void deleteUser(Long id) {
        service.delete(id);
    }
}
