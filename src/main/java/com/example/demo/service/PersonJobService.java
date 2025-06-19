package com.example.demo.service;

import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobUpdateRequest;
import com.example.demo.entity.PersonJobEntity;

import java.util.List;
import java.util.Optional;

public interface PersonJobService {
    List<PersonJobEntity> findByPersonId(Long personId);
    Optional<PersonJobEntity> findByPersonIdAndCurrent(Long personId);
    PersonJobEntity create(PersonJobCreateRequest personJobCreateRequest);
    PersonJobEntity update(PersonJobUpdateRequest personJobUpdateRequest);
}
