package com.example.demo.service;

import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleUpdateRequest;
import com.example.demo.entity.JobTitleEntity;

import java.util.List;

public interface JobTitleService {
    List<JobTitleEntity> findAll();
    JobTitleEntity create(JobTitleCreateRequest jobTitleCreateRequest);
    JobTitleEntity update(JobTitleUpdateRequest jobTitleUpdateRequest);
}
