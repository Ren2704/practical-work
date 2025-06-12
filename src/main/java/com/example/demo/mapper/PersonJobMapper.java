package com.example.demo.mapper;

import com.example.demo.controller.request.job.person.PersonJobCreateRequest;
import com.example.demo.entity.PersonJobEntity;

public interface PersonJobMapper {
    <T extends PersonJobCreateRequest> PersonJobEntity requestMapToPersonJob(PersonJobEntity personJobEntity, T request);
}
