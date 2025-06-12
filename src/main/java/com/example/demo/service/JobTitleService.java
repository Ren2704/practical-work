package com.example.demo.service;

import com.example.demo.controller.request.job.title.JobTitleCreateRequest;
import com.example.demo.controller.request.job.title.JobTitleUpdateRequest;
import com.example.demo.entity.JobTitleEntity;

import java.util.List;
import java.util.Optional;

public interface JobTitleService {
    List<JobTitleEntity> findAll();
    Optional<JobTitleEntity> findById(Long id);
    JobTitleEntity create(JobTitleCreateRequest jobTitleCreateRequest);
    JobTitleEntity update(JobTitleUpdateRequest jobTitleUpdateRequest);
    void delete(Long id);
}
