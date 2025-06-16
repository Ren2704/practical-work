package com.example.demo.service;

import com.example.demo.controller.request.outstanding.people.OutstandingPeopleCreateRequest;
import com.example.demo.controller.request.outstanding.people.OutstandingPeopleUpdateRequest;
import com.example.demo.entity.OutstandingPeopleEntity;

import java.util.List;
import java.util.Optional;

public interface OutstandingPeopleService {
    List<OutstandingPeopleEntity> findAll();
    Optional<OutstandingPeopleEntity> findById(Long id);
    List<OutstandingPeopleEntity> findByName(String name);
    List<OutstandingPeopleEntity> findBySurname(String surname);
    List<OutstandingPeopleEntity> findByNameAndSurname(String name, String surname);
    OutstandingPeopleEntity create(OutstandingPeopleCreateRequest outstandingPeopleCreateRequest);
    OutstandingPeopleEntity update(OutstandingPeopleUpdateRequest outstandingPeopleUpdateRequest);
}
