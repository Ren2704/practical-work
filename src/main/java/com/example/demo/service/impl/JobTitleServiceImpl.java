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
        return jobTitleRepository.findAll();
    }

    @Override
    public Optional<JobTitleEntity> findById(Long id) {
        return jobTitleRepository.findById(id);
    }

    @Override
    public JobTitleEntity create(JobTitleCreateRequest jobTitleCreateRequest) {
        JobTitleEntity jobTitleEntity = new JobTitleEntity();
        jobTitleEntity = jobTitleMapper.requestMapToJobTitle(jobTitleEntity, jobTitleCreateRequest);
        return jobTitleRepository.save(jobTitleEntity);
    }

    @Override
    public JobTitleEntity update(JobTitleUpdateRequest jobTitleUpdateRequest) {
        Optional<JobTitleEntity> optionalJobTitle = jobTitleRepository.findById(jobTitleUpdateRequest.getId());
        if (optionalJobTitle.isPresent()) {
            JobTitleEntity jobTitleEntity = optionalJobTitle.get();
            jobTitleEntity = jobTitleMapper.requestMapToJobTitle(jobTitleEntity, jobTitleUpdateRequest);
            return jobTitleRepository.save(jobTitleEntity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        jobTitleRepository.deleteById(id);
    }
}
