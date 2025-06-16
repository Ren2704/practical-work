package com.example.demo.mapper.impl;


import com.example.demo.controller.request.job.title.JobTitleCreateRequest;
import com.example.demo.entity.JobTitleEntity;
import com.example.demo.mapper.JobTitleMapper;
import org.springframework.stereotype.Component;


@Component
public class JobTitleMapperImpl implements JobTitleMapper {
    @Override
    public <T extends JobTitleCreateRequest> JobTitleEntity requestMapToJobTitle(JobTitleEntity jobTitle, T request) {
        if (request == null)
            return null;
        jobTitle.setName(request.getName());

        return jobTitle;
    }
}
