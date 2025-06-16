package com.example.demo.service.impl;

import com.example.demo.controller.request.job.title.JobTitleCreateRequest;
import com.example.demo.controller.request.job.title.JobTitleUpdateRequest;
import com.example.demo.entity.JobTitleEntity;
import com.example.demo.mapper.JobTitleMapper;
import com.example.demo.repository.JobTitleRepository;
import com.example.demo.service.JobTitleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository jobTitleRepository;
    private final JobTitleMapper jobTitleMapper;

    public JobTitleServiceImpl(JobTitleRepository jobTitleRepository, JobTitleMapper jobTitleMapper) {
        this.jobTitleRepository = jobTitleRepository;
        this.jobTitleMapper = jobTitleMapper;
    }

    @Override
    public List<JobTitleEntity> findAll() {
        return jobTitleRepository.findByDisplayTrueOrderByNameAsc();
    }

    @Override
    public JobTitleEntity create(JobTitleCreateRequest jobTitleCreateRequest) {
        JobTitleEntity jobTitle = new JobTitleEntity();
        jobTitle = jobTitleMapper.requestMapToJobTitle(jobTitle, jobTitleCreateRequest);
        return jobTitleRepository.save(jobTitle);
    }

    @Override
    public JobTitleEntity update(JobTitleUpdateRequest jobTitleUpdateRequest) {
        Optional<JobTitleEntity> optionalJobTitle = jobTitleRepository.findByIdAndDisplayTrue(jobTitleUpdateRequest.getId());
        if (optionalJobTitle.isPresent()) {
            JobTitleEntity jobTitle = optionalJobTitle.get();
            jobTitle = jobTitleMapper.requestMapToJobTitle(jobTitle, jobTitleUpdateRequest);
            return jobTitleRepository.save(jobTitle);
        }
        return null;
    }
}
