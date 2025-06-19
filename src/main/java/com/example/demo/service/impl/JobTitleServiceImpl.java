package com.example.demo.service.impl;

import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleUpdateRequest;
import com.example.demo.entity.JobTitleEntity;
import com.example.demo.mapper.JobTitleMapper;
import com.example.demo.repository.JobTitleRepository;
import com.example.demo.service.JobTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository jobTitleRepository;
    private final JobTitleMapper jobTitleMapper;

    @Override
    @Transactional(readOnly = true)
    public List<JobTitleEntity> findAll() {
        return jobTitleRepository.findByDisplayTrueOrderByNameAsc();
    }

    @Override
    @Transactional
    public JobTitleEntity create(JobTitleCreateRequest jobTitleCreateRequest) {
        JobTitleEntity jobTitleEntity = jobTitleMapper.requestMapToEntity(jobTitleCreateRequest);
        return jobTitleRepository.save(jobTitleEntity);
    }

    @Override
    @Transactional
    public JobTitleEntity update(JobTitleUpdateRequest jobTitleUpdateRequest) {
        Optional<JobTitleEntity> optionalJobTitle = jobTitleRepository.findByIdAndDisplayTrue(jobTitleUpdateRequest.getId());
        if (optionalJobTitle.isPresent()) {
            JobTitleEntity jobTitleEntity = optionalJobTitle.get();
            jobTitleMapper.updateEntity(jobTitleEntity, jobTitleUpdateRequest);
            return jobTitleRepository.save(jobTitleEntity);
        }
        return null;
    }
}
