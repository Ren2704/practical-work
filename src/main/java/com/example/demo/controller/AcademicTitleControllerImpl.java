package com.example.demo.controller;

import com.example.api.AcademicTitleControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.AcademicTitleService;
import com.example.model.*;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AcademicTitleControllerImpl implements AcademicTitleControllerApi {

    private final AcademicTitleService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<AcademicTitleResponse> findAllAcademicTitles() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public List<AcademicTitleResponse> findAllDeletedAcademicTitles() {
        return service.findAllDeleted();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public AcademicTitleResponse findAcademicTitleById(Long id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicTitleResponse findDeletedAcademicTitleById(Long id) {
        return service.findDeletedById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicTitleResponse createAcademicTitle(AcademicTitleCreateRequest academicTitleCreateRequest) {
        return service.create(academicTitleCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicTitleResponse updateAcademicTitle(Long id, AcademicTitleUpdateRequest academicTitleUpdateRequest) {
        return service.update(academicTitleUpdateRequest, id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicTitleResponse recoverAcademicTitle(Long id) {
        return service.recover(id);
    }
}