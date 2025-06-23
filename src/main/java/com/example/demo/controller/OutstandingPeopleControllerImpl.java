package com.example.demo.controller;

import com.example.api.OutstandingPeopleControllerApi;
import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.demo.mapper.OutstandingPeopleMapper;
import com.example.demo.service.OutstandingPeopleService;
import com.example.model.OutstandingPeopleResponse;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonResponse;
import com.example.model.OutstandingPersonUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class OutstandingPeopleControllerImpl implements OutstandingPeopleControllerApi {

    private final OutstandingPeopleService outstandingPeopleService;
    private final OutstandingPeopleMapper outstandingPeopleMapper;
    @Override
    public ResponseEntity<OutstandingPersonResponse> createOutstandingPerson(OutstandingPersonCreateRequest outstandingPersonCreateRequest) {
        OutstandingPeopleEntity outstandingPersonEntity = outstandingPeopleService.create(outstandingPersonCreateRequest);
        return new ResponseEntity<>(outstandingPeopleMapper.entityMapToResponse(outstandingPersonEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<OutstandingPeopleResponse>> findAllOutstandingPeople() {
        List<OutstandingPeopleEntity> outstandingPeopleEntities = outstandingPeopleService.findAll();
        List<OutstandingPeopleResponse> responseList = outstandingPeopleMapper.entityMapToResponseList(outstandingPeopleEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OutstandingPersonResponse> findOutstandingPersonById(Long id) {
        Optional<OutstandingPeopleEntity> OutstandingPersonOptional = outstandingPeopleService.findById(id);
        if (OutstandingPersonOptional.isPresent()) {
            OutstandingPersonResponse response = outstandingPeopleMapper.entityMapToResponse(OutstandingPersonOptional.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<OutstandingPeopleResponse>> findOutstandingPersonByName(String name) {
        List<OutstandingPeopleEntity> outstandingPeopleEntities = outstandingPeopleService.findByName(name);
        if (outstandingPeopleEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<OutstandingPeopleResponse> responseList = outstandingPeopleMapper.entityMapToResponseList(outstandingPeopleEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OutstandingPeopleResponse>> findOutstandingPersonByNameAndSurname(String name, String surname) {
        List<OutstandingPeopleEntity> outstandingPeopleEntities = outstandingPeopleService.findByNameAndSurname(name, surname);
        if (outstandingPeopleEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<OutstandingPeopleResponse> responseList = outstandingPeopleMapper.entityMapToResponseList(outstandingPeopleEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OutstandingPeopleResponse>> findOutstandingPersonBySurname(String surname) {
        List<OutstandingPeopleEntity> outstandingPeopleEntities = outstandingPeopleService.findBySurname(surname);
        if (outstandingPeopleEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<OutstandingPeopleResponse> responses = outstandingPeopleMapper.entityMapToResponseList(outstandingPeopleEntities);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OutstandingPersonResponse> updateOutstandingPerson(Long id, OutstandingPersonUpdateRequest outstandingPersonUpdateRequest) {
        OutstandingPeopleEntity outstandingPersonEntity = outstandingPeopleService.update(outstandingPersonUpdateRequest, id);
        return new ResponseEntity<>(outstandingPeopleMapper.entityMapToResponse(outstandingPersonEntity), HttpStatus.OK);
    }
}
