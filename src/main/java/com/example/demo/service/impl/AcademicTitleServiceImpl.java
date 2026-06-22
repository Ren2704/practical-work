package com.example.demo.service.impl;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.AcademicTitleCreateRequest;
import com.example.model.AcademicTitleResponse;
import com.example.model.AcademicTitleUpdateRequest;
import com.example.demo.dao.entity.AcademicTitleEntity;
import com.example.demo.mapper.AcademicTitleMapper;
import com.example.demo.dao.repository.AcademicTitleRepository;
import com.example.demo.service.AcademicTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicTitleServiceImpl implements AcademicTitleService {

    private final AcademicTitleRepository repository;
    private final AcademicTitleMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<AcademicTitleResponse> findAll() {
        List<AcademicTitleEntity> academicTitleEntity = repository.findByIsDeletedFalseOrderByNameAsc();
        return academicTitleEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicTitleResponse> findAllDeleted() {
        List<AcademicTitleEntity> academicTitleEntity = repository.findByIsDeletedTrueOrderByNameAsc();
        return academicTitleEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicTitleResponse findById(Long id) {
        AcademicTitleEntity academicTitleEntity = findAcademicTitleById(id);
        return mapper.toResponse(academicTitleEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicTitleResponse findDeletedById(Long id) {
        AcademicTitleEntity academicTitleEntity = findDeletedAcademicTitleById(id);
        return mapper.toResponse(academicTitleEntity);
    }

    @Override
    @Transactional
    public AcademicTitleResponse create(AcademicTitleCreateRequest academicTitleCreateRequest) {
        if (repository.existsByNameIgnoreCase(academicTitleCreateRequest.getName())) {
            throw new AlreadyExistsException(
                    "Academic title with name '" + academicTitleCreateRequest.getName() + "' already exists"
            );
        }
        AcademicTitleEntity academicTitleEntity = mapper.toEntity(academicTitleCreateRequest);
        repository.save(academicTitleEntity);
        return mapper.toResponse(academicTitleEntity);
    }

    @Override
    @Transactional
    public AcademicTitleResponse update(AcademicTitleUpdateRequest academicTitleUpdateRequest, Long id) {
        AcademicTitleEntity academicTitleEntity = findAcademicTitleById(id);
        mapper.updateEntity(academicTitleEntity,academicTitleUpdateRequest);
        return mapper.toResponse(academicTitleEntity);
    }

    @Override
    @Transactional
    public AcademicTitleResponse recover(Long id) {
        AcademicTitleEntity academicTitleEntity = findDeletedAcademicTitleById(id);
        academicTitleEntity.setIsDeleted(false);
        return mapper.toResponse(academicTitleEntity);
    }

    private AcademicTitleEntity findAcademicTitleById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("Academic title not found with id: " + id)
        );
    }

    private AcademicTitleEntity findDeletedAcademicTitleById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException("Deleted academic title not found with id: " + id)
        );
    }
}
