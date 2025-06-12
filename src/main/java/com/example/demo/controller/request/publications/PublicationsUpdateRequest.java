package com.example.demo.controller.request.publications;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicationsUpdateRequest extends PublicationsCreateRequest {
    private Long id;
}
