package com.example.demo.controller;

import com.example.api.JobTitleControllerApi;
import com.example.demo.entity.JobTitleEntity;
import com.example.demo.mapper.JobTitleMapper;
import com.example.demo.service.JobTitleService;
import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleResponse;
import com.example.model.JobTitleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobTitleControllerImpl implements JobTitleControllerApi {

    private final JobTitleService jobTitleService;
    private final JobTitleMapper jobTitleMapper;
    @Override
    public ResponseEntity<JobTitleResponse> createJobTitle(JobTitleCreateRequest jobTitleCreateRequest) {
        JobTitleEntity jobTitleEntity = jobTitleService.create(jobTitleCreateRequest);
        return new ResponseEntity<>(jobTitleMapper.entityMapToResponse(jobTitleEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<JobTitleResponse>> findAllJobTitles() {
        List<JobTitleEntity> jobTitleEntities = jobTitleService.findAll();
        List<JobTitleResponse> responseList = jobTitleMapper.entityMapToResponseList(jobTitleEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JobTitleResponse> updateJobTitle(Long id, JobTitleUpdateRequest jobTitleUpdateRequest) {
        JobTitleEntity jobTitleEntity = jobTitleService.update(jobTitleUpdateRequest, id);
        return new ResponseEntity<>(jobTitleMapper.entityMapToResponse(jobTitleEntity), HttpStatus.OK);
    }
}
