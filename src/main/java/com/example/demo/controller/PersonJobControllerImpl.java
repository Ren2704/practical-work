package com.example.demo.controller;

import com.example.api.PersonJobControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.PersonJobService;
import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobResponse;
import com.example.model.PersonJobUpdateRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonJobControllerImpl implements PersonJobControllerApi {

    private final PersonJobService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<PersonJobResponse> findAllPersonJob() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public List<PersonJobResponse> findAllDeletedPersonJob() {
        return service.findAllDeleted();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public PersonJobResponse findPersonJobById(Long id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public PersonJobResponse findDeletedPersonJobById(Long id) {
        return service.findDeletedById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<PersonJobResponse> findPersonJobByPersonId(Long personId) {
        return service.findByPersonId(personId);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public PersonJobResponse findPersonJobByPersonIdAndCurrent(Long personId) {
        return service.findByPersonIdAndCurrent(personId);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public PersonJobResponse createPersonJob(PersonJobCreateRequest personJobCreateRequest) {
        return service.create(personJobCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public PersonJobResponse updatePersonJob(Long id, PersonJobUpdateRequest personJobUpdateRequest) {
        return service.update(personJobUpdateRequest,id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public PersonJobResponse recoverPersonJob(Long id) {
        return service.recover(id);
    }
}
