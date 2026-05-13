package com.example.demo.service.impl;

import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.AcademicDegreeCreateRequest;
import com.example.model.AcademicDegreeResponse;
import com.example.model.AcademicDegreeUpdateRequest;
import com.example.demo.dao.entity.AcademicDegreeEntity;
import com.example.demo.mapper.AcademicDegreeMapper;
import com.example.demo.dao.repository.AcademicDegreeRepository;
import com.example.demo.service.AcademicDegreeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AcademicDegreeServiceImpl implements AcademicDegreeService {

    private final AcademicDegreeRepository repository;
    private final AcademicDegreeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDegreeResponse> findAll() {
        List<AcademicDegreeEntity> academicDegreeEntity = repository.findByIsDeletedFalseOrderByNameAsc();
        return academicDegreeEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDegreeResponse> findAllDeleted() {
        List<AcademicDegreeEntity> academicDegreeEntity = repository.findByIsDeletedTrueOrderByNameAsc();
        return academicDegreeEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicDegreeResponse findById(Long id) {
        AcademicDegreeEntity academicDegreeEntity = findAcademicDegreeById(id);
        return mapper.toResponse(academicDegreeEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public AcademicDegreeResponse findDeletedById(Long id) {
        AcademicDegreeEntity academicDegreeEntity = findDeletedAcademicDegreeById(id);
        return mapper.toResponse(academicDegreeEntity);
    }

    @Override
    @Transactional
    public AcademicDegreeResponse create(AcademicDegreeCreateRequest academicDegreeCreateRequest) {
        if (repository.existsByNameIgnoreCase(academicDegreeCreateRequest.getName())) {
            throw new AlreadyExistsException(
                    "Academic degree with name '" + academicDegreeCreateRequest.getName() + "' already exists"
            );
        }
        AcademicDegreeEntity academicDegreeEntity = mapper.toEntity(academicDegreeCreateRequest);
        repository.save(academicDegreeEntity);
        return mapper.toResponse(academicDegreeEntity);
    }

    @Override
    @Transactional
    public AcademicDegreeResponse update(AcademicDegreeUpdateRequest academicDegreeUpdateRequest, Long id) {
        AcademicDegreeEntity academicDegreeEntity = findAcademicDegreeById(id);
        mapper.updateEntity(academicDegreeEntity, academicDegreeUpdateRequest);
        return mapper.toResponse(academicDegreeEntity);
    }

    @Override
    @Transactional
    public AcademicDegreeResponse recover(Long id) {
        AcademicDegreeEntity academicDegreeEntity = findDeletedAcademicDegreeById(id);
        academicDegreeEntity.setIsDeleted(false);
        return mapper.toResponse(academicDegreeEntity);
    }

    private AcademicDegreeEntity findAcademicDegreeById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("Academic degree not found with id: " + id)
        );
    }

    private AcademicDegreeEntity findDeletedAcademicDegreeById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException("Deleted academic degree not found with id: " + id)
        );
    }
}
