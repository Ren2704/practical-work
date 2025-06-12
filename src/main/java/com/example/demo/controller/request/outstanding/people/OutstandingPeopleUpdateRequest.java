package com.example.demo.controller.request.outstanding.people;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutstandingPeopleUpdateRequest extends OutstandingPeopleCreateRequest {
    private Long id;
}
