package com.example.demo.service;

import com.example.demo.controller.request.outstanding.people.OutstandingPeopleCreateRequest;
import com.example.demo.controller.request.outstanding.people.OutstandingPeopleUpdateRequest;
import com.example.demo.entity.OutstandingPeopleEntity;

import java.util.List;
import java.util.Optional;

public interface OutstandingPeopleService {
    List<OutstandingPeopleEntity> findAll();
    Optional<OutstandingPeopleEntity> findById(Long id);
    OutstandingPeopleEntity create(OutstandingPeopleCreateRequest outstandingPeopleCreateRequest);
    OutstandingPeopleEntity update(OutstandingPeopleUpdateRequest outstandingPeopleUpdateRequest);
    void delete(Long id);
}
