package com.example.demo.service.impl;

import com.example.demo.controller.request.users.UsersCreateRequest;
import com.example.demo.controller.request.users.UsersUpdateRequest;
import com.example.demo.entity.UsersEntity;
import com.example.demo.mapper.UsersMapper;
import com.example.demo.repository.UsersRepository;
import com.example.demo.service.UsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    public UsersServiceImpl(UsersRepository usersRepository, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = usersMapper;
    }

    @Override
    public List<UsersEntity> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<UsersEntity> findById(Long id) {
        return usersRepository.findById(id);
    }

    @Override
    public UsersEntity create(UsersCreateRequest usersCreateRequest) {
        UsersEntity users = new UsersEntity();
        users = usersMapper.requestMapToUsers(users, usersCreateRequest);
        return usersRepository.save(users);
    }

    @Override
    public UsersEntity update(UsersUpdateRequest usersUpdateRequest) {
        Optional<UsersEntity> optionalUsers = usersRepository.findById(usersUpdateRequest.getId());
        if (optionalUsers.isPresent()) {
            UsersEntity users = optionalUsers.get();
            users = usersMapper.requestMapToUsers(users, usersUpdateRequest);
            return usersRepository.save(users);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }
}

