package com.example.demo.service;

import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleResponse;
import com.example.model.JobTitleUpdateRequest;

import java.util.List;

public interface JobTitleService {
    List<JobTitleResponse> findAll();
    List<JobTitleResponse> findAllDeleted();
    JobTitleResponse findById(Long id);
    JobTitleResponse findDeletedById(Long id);
    JobTitleResponse create(JobTitleCreateRequest jobTitleCreateRequest);
    JobTitleResponse update(JobTitleUpdateRequest jobTitleUpdateRequest, Long id);
    JobTitleResponse recover (Long id);
}
