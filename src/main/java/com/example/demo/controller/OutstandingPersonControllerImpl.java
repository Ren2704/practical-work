package com.example.demo.controller;

import com.example.api.OutstandingPersonControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.OutstandingPersonService;
import com.example.model.OutstandingPersonResponse;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.PersonResponse;
import com.example.model.OutstandingPersonUpdateRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OutstandingPersonControllerImpl implements OutstandingPersonControllerApi {

    private final OutstandingPersonService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findAllOutstandingPeople() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public List<PersonResponse> findAllDeletedOutstandingPeople() {
        return service.findAllDeleted();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public OutstandingPersonResponse findOutstandingPersonById(Long id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public OutstandingPersonResponse findDeletedOutstandingPersonById(Long id) {
        return service.findDeletedById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findOutstandingPersonByName(String name) {
        return service.findByName(name);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findOutstandingPersonBySurname(String surname) {
        return service.findBySurname(surname);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> findOutstandingPersonByNameAndSurname(String name, String surname) {
        return service.findByNameAndSurname(name,surname);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public OutstandingPersonResponse createOutstandingPerson(OutstandingPersonCreateRequest outstandingPersonCreateRequest) {
        return service.create(outstandingPersonCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public OutstandingPersonResponse updateOutstandingPerson(Long id, OutstandingPersonUpdateRequest outstandingPersonUpdateRequest) {
        return service.update(outstandingPersonUpdateRequest, id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public OutstandingPersonResponse recoverOutstandingPerson(Long id) {
        return service.recover(id);
    }
}
