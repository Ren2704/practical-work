package com.example.demo.controller;

import com.example.api.AcademicDegreeControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.AcademicDegreeService;
import com.example.model.AcademicDegreeCreateRequest;
import com.example.model.AcademicDegreeResponse;
import com.example.model.AcademicDegreeUpdateRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AcademicDegreeControllerImpl implements AcademicDegreeControllerApi {

    private final AcademicDegreeService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<AcademicDegreeResponse> findAllAcademicDegrees() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public List<AcademicDegreeResponse> findAllDeletedAcademicDegrees() {
        return service.findAllDeleted();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public AcademicDegreeResponse findAcademicDegreeById(Long id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicDegreeResponse findDeletedAcademicDegreeById(Long id) {
        return service.findDeletedById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicDegreeResponse createAcademicDegree(AcademicDegreeCreateRequest academicDegreeCreateRequest) {
        return service.create(academicDegreeCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicDegreeResponse updateAcademicDegree(Long id, AcademicDegreeUpdateRequest academicDegreeUpdateRequest) {
        return service.update(academicDegreeUpdateRequest, id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AcademicDegreeResponse recoverAcademicDegree(Long id) {
        return service.recover(id);
    }
}
