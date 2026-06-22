package com.example.demo.controller;

import com.example.api.JobTitleControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.JobTitleService;
import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleResponse;
import com.example.model.JobTitleUpdateRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobTitleControllerImpl implements JobTitleControllerApi {

    private final JobTitleService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<JobTitleResponse> findAllJobTitles() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public List<JobTitleResponse> findAllDeletedJobTitles() {
        return service.findAllDeleted();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public JobTitleResponse findJobTitleById(Long id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public JobTitleResponse findDeletedJobTitleById(Long id) {
        return service.findDeletedById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public JobTitleResponse createJobTitle(JobTitleCreateRequest jobTitleCreateRequest) {
        return service.create(jobTitleCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public JobTitleResponse updateJobTitle(Long id, JobTitleUpdateRequest jobTitleUpdateRequest) {
        return service.update(jobTitleUpdateRequest,id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public JobTitleResponse recoverJobTitle(Long id) {
        return service.recover(id);
    }
}
