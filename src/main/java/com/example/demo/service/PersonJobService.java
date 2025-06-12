package com.example.demo.service;

import com.example.demo.controller.request.job.person.PersonJobCreateRequest;
import com.example.demo.controller.request.job.person.PersonJobUpdateRequest;
import com.example.demo.entity.PersonJobEntity;

import java.util.List;
import java.util.Optional;

public interface PersonJobService {
    List<PersonJobEntity> findAll();
    Optional<PersonJobEntity> findById(Long id);
    PersonJobEntity create(PersonJobCreateRequest personJobCreateRequest);
    PersonJobEntity update(PersonJobUpdateRequest personJobUpdateRequest);
    void delete(Long id);
}
