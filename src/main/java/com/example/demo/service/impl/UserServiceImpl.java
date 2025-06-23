package com.example.demo.service.impl;

import com.example.model.UserCreateRequest;
import com.example.model.UserUpdateRequest;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return usersRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    @Transactional
    public UserEntity create(UserCreateRequest userCreateRequest) {
        UserEntity userEntity = userMapper.requestMapToEntity(userCreateRequest);
        return usersRepository.save(userEntity);
    }

    @Override
    @Transactional
    public UserEntity update(UserUpdateRequest userUpdateRequest, Long id) {
        Optional<UserEntity> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            UserEntity userEntity = optionalUser.get();
            userMapper.updateEntity(userEntity, userUpdateRequest);
            return usersRepository.save(userEntity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}

