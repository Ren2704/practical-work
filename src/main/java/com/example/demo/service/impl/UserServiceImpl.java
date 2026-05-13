package com.example.demo.service.impl;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import com.example.model.UserUpdateRequest;
import com.example.demo.dao.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        List<UserEntity> userEntity = repository.findAll();
        return userEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        UserEntity userEntity = findUserById(id);
        return mapper.toResponse(userEntity);
    }

    @Override
    @Transactional
    public UserResponse create(UserCreateRequest userCreateRequest) {
        if (repository.existsByLogin(userCreateRequest.getLogin())) {
            throw new AlreadyExistsException(
                    "User with login '" + userCreateRequest.getLogin() + "' already exists"
            );
        }
        UserEntity userEntity = mapper.toEntity(userCreateRequest);
        repository.save(userEntity);
        return mapper.toResponse(userEntity);
    }

    @Override
    @Transactional
    public UserResponse update(UserUpdateRequest userUpdateRequest, Long id) {
        UserEntity userEntity = findUserById(id);
        mapper.updateEntity(userEntity,userUpdateRequest);
        return mapper.toResponse(userEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private UserEntity findUserById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found with id: " + id)
        );
    }
}