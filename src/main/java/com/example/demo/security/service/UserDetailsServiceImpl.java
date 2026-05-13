package com.example.demo.security.service;

import com.example.demo.dao.entity.UserEntity;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.security.entity.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + login)
        );
        return new UserDetailsImpl(userEntity);
    }
}