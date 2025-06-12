package com.example.demo.controller.request.job.person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonJobUpdateRequest extends PersonJobCreateRequest{
    private Long id;
}
