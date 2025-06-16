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
        return academicDegreesRepository.findByDisplayTrueOrderByNameAsc();
    }

    @Override
    public AcademicDegreesEntity create(AcademicDegreesCreateRequest academicDegreesCreateRequest) {
        AcademicDegreesEntity academicDegrees = new AcademicDegreesEntity();
        academicDegrees = academicDegreesMapper.requestMapToAcademicDegrees(academicDegrees, academicDegreesCreateRequest);
        return academicDegreesRepository.save(academicDegrees);
    }

    @Override
    public AcademicDegreesEntity update(AcademicDegreesUpdateRequest academicDegreesUpdateRequest) {
        Optional<AcademicDegreesEntity> optionalAcademicDegrees = academicDegreesRepository.findByIdAndDisplayTrue(academicDegreesUpdateRequest.getId());
        if (optionalAcademicDegrees.isPresent()) {
            AcademicDegreesEntity academicDegrees = optionalAcademicDegrees.get();
            academicDegrees = academicDegreesMapper.requestMapToAcademicDegrees(academicDegrees, academicDegreesUpdateRequest);
            return academicDegreesRepository.save(academicDegrees);
        }
        return null;
    }
}
