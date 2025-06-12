package com.example.demo.controller.request.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersUpdateRequest extends UsersCreateRequest{
    private Long id;
}
