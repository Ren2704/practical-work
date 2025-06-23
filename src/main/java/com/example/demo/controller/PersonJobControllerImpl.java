package com.example.demo.controller;

import com.example.api.PersonJobControllerApi;
import com.example.demo.entity.PersonJobEntity;
import com.example.demo.mapper.PersonJobMapper;
import com.example.demo.service.PersonJobService;
import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobResponse;
import com.example.model.PersonJobUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersonJobControllerImpl implements PersonJobControllerApi {

    private final PersonJobService personJobService;
    private final PersonJobMapper personJobMapper;
    @Override
    public ResponseEntity<PersonJobResponse> createPersonJob(PersonJobCreateRequest personJobCreateRequest) {
        PersonJobEntity personJobEntity = personJobService.create(personJobCreateRequest);
        return new ResponseEntity<>(personJobMapper.entityMapToResponse(personJobEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<PersonJobResponse>> findPersonJobByPersonId(Long personId) {
        List<PersonJobEntity> personJobsEntities = personJobService.findByPersonId(personId);
        if (personJobsEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PersonJobResponse> responseList = personJobMapper.entityMapToResponseList(personJobsEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PersonJobResponse> findPersonJobByPersonIdAndCurrent(Long personId) {
        Optional<PersonJobEntity> personJobOptional = personJobService.findByPersonIdAndCurrent(personId);
        if (personJobOptional.isPresent()) {
            PersonJobResponse response = personJobMapper.entityMapToResponse(personJobOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<PersonJobResponse> updatePersonJob(Long id, PersonJobUpdateRequest personJobUpdateRequest) {
        PersonJobEntity personJobEntity = personJobService.update(personJobUpdateRequest, id);
        return new ResponseEntity<>(personJobMapper.entityMapToResponse(personJobEntity), HttpStatus.OK);
    }
}
