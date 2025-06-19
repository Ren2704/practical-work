package com.example.demo.service.impl;

import com.example.model.AcademicDegreesCreateRequest;
import com.example.model.AcademicDegreesUpdateRequest;
import com.example.demo.entity.AcademicDegreesEntity;
import com.example.demo.mapper.AcademicDegreesMapper;
import com.example.demo.repository.AcademicDegreesRepository;
import com.example.demo.service.AcademicDegreesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcademicDegreesServiceImpl implements AcademicDegreesService {

    private final AcademicDegreesRepository academicDegreesRepository;
    private final AcademicDegreesMapper academicDegreesMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AcademicDegreesEntity> findAll() {
        return academicDegreesRepository.findByDisplayTrueOrderByNameAsc();
    }

    @Override
    @Transactional
    public AcademicDegreesEntity create(AcademicDegreesCreateRequest academicDegreesCreateRequest) {
        AcademicDegreesEntity academicDegreesEntity  = academicDegreesMapper.requestMapToEntity(academicDegreesCreateRequest);
        return academicDegreesRepository.save(academicDegreesEntity);
    }

    @Override
    @Transactional
    public AcademicDegreesEntity update(AcademicDegreesUpdateRequest academicDegreesUpdateRequest) {
        Optional<AcademicDegreesEntity> optionalAcademicDegrees  = academicDegreesRepository.findByIdAndDisplayTrue(academicDegreesUpdateRequest.getId());
        if (optionalAcademicDegrees.isPresent()) {
            AcademicDegreesEntity academicDegreesEntity = optionalAcademicDegrees.get();
            academicDegreesMapper.updateEntity(academicDegreesEntity, academicDegreesUpdateRequest);
            return academicDegreesRepository.save(academicDegreesEntity);
        }
        return null;
    }
}
