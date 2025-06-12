package com.example.demo.controller.request.job.person;

import lombok.Data;

@Data
public class PersonJobCreateRequest {
    private int startYear;
    private int endYear;
    private boolean isCurrent = false;

    // зависимости
    private Long personId;
    private String jobTitle;
}
