package com.example.demo.controller;

import com.example.api.OutstandingPersonControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.OutstandingPersonService;
import com.example.model.*;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OutstandingPersonControllerImpl implements OutstandingPersonControllerApi {

    private final OutstandingPersonService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public PersonSliceResponse findAllOutstandingPeople(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findAll(pageable);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public PersonSliceResponse findAllDeletedOutstandingPeople(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findAllDeleted(pageable);
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
    public PersonSliceResponse findOutstandingPersonByName(String name, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name"));
        return service.findByName(name, pageable);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public PersonSliceResponse findOutstandingPersonBySurname(String surname, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("surname"));
        return service.findBySurname(surname, pageable);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public PersonSliceResponse findOutstandingPersonByNameAndSurname(String name, String surname, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("surname"));
        return service.findByNameAndSurname(name, surname, pageable);
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

    @Override
    @ResponseStatus(HttpStatus.OK)
    public Resource getPersonPhoto(Long id) {
        return service.getPhoto(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public PhotoResponse updatePersonPhoto(Long id, MultipartFile photo) {
        return service.updatePhoto(id, photo);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<PersonResponse> recognizePersonPhoto(MultipartFile photo) {
        return service.recognizePhoto(photo);
    }
}