package com.example.demo.controller.request.users;

import com.example.demo.enums.Role;
import lombok.Data;

@Data
public class UsersCreateRequest {
    private String login;
    private String password;
    private Role role = Role.NOT_SELECTED;
}
