package com.example.demo.mapper;

import com.example.demo.controller.request.job.title.JobTitleCreateRequest;
import com.example.demo.entity.JobTitleEntity;

public interface JobTitleMapper {
    <T extends JobTitleCreateRequest> JobTitleEntity requestMapToJobTitle(JobTitleEntity jobTitleEntity, T request);
}
