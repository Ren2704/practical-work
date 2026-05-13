package com.example.demo.service;

import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobResponse;
import com.example.model.PersonJobUpdateRequest;

import java.util.List;

public interface PersonJobService {
    List<PersonJobResponse> findAll();
    List<PersonJobResponse> findAllDeleted();
    List<PersonJobResponse> findByPersonId(Long personId);
    PersonJobResponse findByPersonIdAndCurrent(Long personId);
    PersonJobResponse findById(Long id);
    PersonJobResponse findDeletedById(Long id);
    PersonJobResponse create(PersonJobCreateRequest personJobCreateRequest);
    PersonJobResponse update(PersonJobUpdateRequest personJobUpdateRequest, Long id);
    PersonJobResponse recover (Long id);
}
