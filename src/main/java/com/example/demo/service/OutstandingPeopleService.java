package com.example.demo.service;

import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonUpdateRequest;
import com.example.demo.entity.OutstandingPeopleEntity;

import java.util.List;
import java.util.Optional;

public interface OutstandingPeopleService {
    List<OutstandingPeopleEntity> findAll();
    Optional<OutstandingPeopleEntity> findById(Long id);
    List<OutstandingPeopleEntity> findByName(String name);
    List<OutstandingPeopleEntity> findBySurname(String surname);
    List<OutstandingPeopleEntity> findByNameAndSurname(String name, String surname);
    OutstandingPeopleEntity create(OutstandingPersonCreateRequest outstandingPeopleCreateRequest);
    OutstandingPeopleEntity update(OutstandingPersonUpdateRequest outstandingPeopleUpdateRequest);
}
