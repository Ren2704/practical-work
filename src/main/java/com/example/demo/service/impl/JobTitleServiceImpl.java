package com.example.demo.service.impl;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleResponse;
import com.example.model.JobTitleUpdateRequest;
import com.example.demo.dao.entity.JobTitleEntity;
import com.example.demo.mapper.JobTitleMapper;
import com.example.demo.dao.repository.JobTitleRepository;
import com.example.demo.service.JobTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobTitleServiceImpl implements JobTitleService {

    private final JobTitleRepository repository;
    private final JobTitleMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<JobTitleResponse> findAll() {
        List<JobTitleEntity> jobTitleEntity = repository.findByIsDeletedFalseOrderByNameAsc();
        return jobTitleEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<JobTitleResponse> findAllDeleted() {
        List<JobTitleEntity> jobTitleEntity = repository.findByIsDeletedTrueOrderByNameAsc();
        return jobTitleEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public JobTitleResponse findById(Long id) {
        JobTitleEntity jobTitleEntity = findJobTitleById(id);
        return mapper.toResponse(jobTitleEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public JobTitleResponse findDeletedById(Long id) {
        JobTitleEntity jobTitleEntity = findDeletedJobTitleById(id);
        return mapper.toResponse(jobTitleEntity);
    }

    @Override
    @Transactional
    public JobTitleResponse create(JobTitleCreateRequest jobTitleCreateRequest) {
        if (repository.existsByNameIgnoreCase(jobTitleCreateRequest.getName())) {
            throw new AlreadyExistsException(
                    "Job title with name '" + jobTitleCreateRequest.getName() + "' already exists"
            );
        }
        JobTitleEntity jobTitleEntity = mapper.toEntity(jobTitleCreateRequest);
        repository.save(jobTitleEntity);
        return mapper.toResponse(jobTitleEntity);
    }

    @Override
    @Transactional
    public JobTitleResponse update(JobTitleUpdateRequest jobTitleUpdateRequest, Long id) {
        JobTitleEntity jobTitleEntity = findJobTitleById(id);
        mapper.updateEntity(jobTitleEntity,jobTitleUpdateRequest);
        return mapper.toResponse(jobTitleEntity);
    }

    @Override
    @Transactional
    public JobTitleResponse recover(Long id) {
        JobTitleEntity jobTitleEntity = findDeletedJobTitleById(id);
        jobTitleEntity.setIsDeleted(false);
        return mapper.toResponse(jobTitleEntity);
    }

    private JobTitleEntity findJobTitleById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("Job title not found with id: " + id)
        );
    }

    private JobTitleEntity findDeletedJobTitleById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException("Deleted job title not found with id: " + id)
        );
    }
}
