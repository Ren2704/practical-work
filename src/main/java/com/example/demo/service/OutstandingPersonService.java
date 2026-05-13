package com.example.demo.service;

import com.example.model.OutstandingPersonResponse;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonUpdateRequest;
import com.example.model.PersonResponse;

import java.util.List;

public interface OutstandingPersonService {
    List<PersonResponse> findAll();
    List<PersonResponse> findAllDeleted();
    OutstandingPersonResponse findById(Long id);
    OutstandingPersonResponse findDeletedById(Long id);
    List<PersonResponse> findByName(String name);
    List<PersonResponse> findBySurname(String surname);
    List<PersonResponse> findByNameAndSurname(String name, String surname);
    OutstandingPersonResponse create(OutstandingPersonCreateRequest outstandingPersonCreateRequest);
    OutstandingPersonResponse update(OutstandingPersonUpdateRequest outstandingPersonUpdateRequest, Long id);
    OutstandingPersonResponse recover (Long id);
}
