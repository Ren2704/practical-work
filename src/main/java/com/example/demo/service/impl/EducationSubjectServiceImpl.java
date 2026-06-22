package com.example.demo.service.impl;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectResponse;
import com.example.model.EducationSubjectUpdateRequest;
import com.example.demo.dao.entity.EducationSubjectEntity;
import com.example.demo.mapper.EducationSubjectMapper;
import com.example.demo.dao.repository.EducationSubjectRepository;
import com.example.demo.service.EducationSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationSubjectServiceImpl implements EducationSubjectService {

    private final EducationSubjectRepository repository;
    private final EducationSubjectMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<EducationSubjectResponse> findAll() {
        List<EducationSubjectEntity> educationSubjectEntity = repository.findByIsDeletedFalseOrderByNameAsc();
        return educationSubjectEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EducationSubjectResponse> findAllDeleted() {
        List<EducationSubjectEntity> educationSubjectEntity = repository.findByIsDeletedTrueOrderByNameAsc();
        return educationSubjectEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public EducationSubjectResponse findById(Long id) {
        EducationSubjectEntity educationSubjectEntity = findEducationSubjectById(id);
        return mapper.toResponse(educationSubjectEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public EducationSubjectResponse findDeletedById(Long id) {
        EducationSubjectEntity educationSubjectEntity = findDeletedEducationSubjectById(id);
        return mapper.toResponse(educationSubjectEntity);
    }

    @Override
    @Transactional
    public EducationSubjectResponse create(EducationSubjectCreateRequest educationSubjectCreateRequest) {
        if (repository.existsByNameIgnoreCase(educationSubjectCreateRequest.getName())) {
            throw new AlreadyExistsException(
                    "Education subject with name '" + educationSubjectCreateRequest.getName() + "' already exists"
            );
        }
        EducationSubjectEntity educationSubjectEntity = mapper.toEntity(educationSubjectCreateRequest);
        repository.save(educationSubjectEntity);
        return mapper.toResponse(educationSubjectEntity);
    }

    @Override
    @Transactional
    public EducationSubjectResponse update(EducationSubjectUpdateRequest educationSubjectUpdateRequest, Long id) {
        EducationSubjectEntity educationSubjectEntity = findEducationSubjectById(id);
        mapper.updateEntity(educationSubjectEntity,educationSubjectUpdateRequest);
        return mapper.toResponse(educationSubjectEntity);
    }

    @Override
    @Transactional
    public EducationSubjectResponse recover(Long id) {
        EducationSubjectEntity educationSubjectEntity = findDeletedEducationSubjectById(id);
        educationSubjectEntity.setIsDeleted(false);
        return mapper.toResponse(educationSubjectEntity);
    }

    private EducationSubjectEntity findEducationSubjectById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("Education subject not found with id: " + id)
        );
    }

    private EducationSubjectEntity findDeletedEducationSubjectById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException("Deleted education subject not found with id: " + id)
        );
    }
}