package com.example.demo.service.impl;

import com.example.demo.controller.request.academic.degrees.AcademicDegreesCreateRequest;
import com.example.demo.controller.request.academic.degrees.AcademicDegreesUpdateRequest;
import com.example.demo.entity.AcademicDegreesEntity;
import com.example.demo.mapper.AcademicDegreesMapper;
import com.example.demo.repository.AcademicDegreesRepository;
import com.example.demo.service.AcademicDegreesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicDegreesServiceImpl implements AcademicDegreesService {

    private final AcademicDegreesRepository academicDegreesRepository;
    private final AcademicDegreesMapper academicDegreesMapper;

    public AcademicDegreesServiceImpl(AcademicDegreesRepository academicDegreesRepository, AcademicDegreesMapper academicDegreesMapper) {
        this.academicDegreesRepository = academicDegreesRepository;
        this.academicDegreesMapper = academicDegreesMapper;
    }

    @Override
    public List<AcademicDegreesEntity> findAll() {
        return academicDegreesRepository.findAll();
    }

    @Override
    public Optional<AcademicDegreesEntity> findById(Long id) {
        return academicDegreesRepository.findById(id);
    }

    @Override
    public AcademicDegreesEntity create(AcademicDegreesCreateRequest academicDegreesCreateRequest) {
        AcademicDegreesEntity academicDegreesEntity = new AcademicDegreesEntity();
        academicDegreesEntity = academicDegreesMapper.requestMapToAcademicDegrees(academicDegreesEntity, academicDegreesCreateRequest);
        return academicDegreesRepository.save(academicDegreesEntity);
    }

    @Override
    public AcademicDegreesEntity update(AcademicDegreesUpdateRequest academicDegreesUpdateRequest) {
        Optional<AcademicDegreesEntity> optionalAcademicDegrees = academicDegreesRepository.findById(academicDegreesUpdateRequest.getId());
        if (optionalAcademicDegrees.isPresent()) {
            AcademicDegreesEntity academicDegreesEntity = optionalAcademicDegrees.get();
            academicDegreesEntity = academicDegreesMapper.requestMapToAcademicDegrees(academicDegreesEntity, academicDegreesUpdateRequest);
            return academicDegreesRepository.save(academicDegreesEntity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        academicDegreesRepository.deleteById(id);
    }
}
