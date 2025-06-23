package com.example.demo.controller;

import com.example.api.UsersControllerApi;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import com.example.model.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UsersControllerImpl implements UsersControllerApi {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<UserResponse> createUser(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userService.create(userCreateRequest);
        return new ResponseEntity<>(userMapper.entityMapToResponse(userEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        List<UserEntity> userEntity = userService.findAll();
        List<UserResponse> responseList = userMapper.entityMapToResponseList(userEntity);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponse> findUserById(Long id) {
        Optional<UserEntity> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            UserResponse response = userMapper.entityMapToResponse(userEntity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = userService.update(userUpdateRequest, id);
        return new ResponseEntity<>(userMapper.entityMapToResponse(userEntity), HttpStatus.OK);
    }
}
