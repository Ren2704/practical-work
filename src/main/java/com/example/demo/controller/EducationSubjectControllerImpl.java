package com.example.demo.controller;

import com.example.api.EducationSubjectControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.EducationSubjectService;
import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectResponse;
import com.example.model.EducationSubjectUpdateRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EducationSubjectControllerImpl implements EducationSubjectControllerApi {

    private final EducationSubjectService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<EducationSubjectResponse> findAllEducationSubjects() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public List<EducationSubjectResponse> findAllDeletedEducationSubjects() {
        return service.findAllDeleted();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public EducationSubjectResponse findEducationSubjectById(Long  id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public EducationSubjectResponse findDeletedEducationSubjectById(Long  id) {
        return service.findDeletedById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public EducationSubjectResponse createEducationSubject(EducationSubjectCreateRequest educationSubjectCreateRequest) {
        return service.create(educationSubjectCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public EducationSubjectResponse updateEducationSubject(Long id, EducationSubjectUpdateRequest educationSubjectUpdateRequest) {
        return service.update(educationSubjectUpdateRequest,id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public EducationSubjectResponse recoverEducationSubject(Long id) {
        return service.recover(id);
    }
}
