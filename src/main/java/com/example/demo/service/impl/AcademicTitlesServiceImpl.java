package com.example.demo.service.impl;

import com.example.model.AcademicTitlesCreateRequest;
import com.example.model.AcademicTitlesUpdateRequest;
import com.example.demo.entity.AcademicTitlesEntity;
import com.example.demo.mapper.AcademicTitlesMapper;
import com.example.demo.repository.AcademicTitlesRepository;
import com.example.demo.service.AcademicTitlesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AcademicTitlesServiceImpl implements AcademicTitlesService {

    private final AcademicTitlesRepository academicTitlesRepository;
    private final AcademicTitlesMapper academicTitlesMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AcademicTitlesEntity> findAll() {
        return academicTitlesRepository.findByDisplayTrueOrderByNameAsc();
    }

    @Override
    @Transactional
    public AcademicTitlesEntity create(AcademicTitlesCreateRequest academicTitlesCreateRequest) {
        AcademicTitlesEntity academicTitlesEntity = academicTitlesMapper.requestMapToEntity(academicTitlesCreateRequest);
        return academicTitlesRepository.save(academicTitlesEntity);
    }

    @Override
    @Transactional
    public AcademicTitlesEntity update(AcademicTitlesUpdateRequest academicTitlesUpdateRequest, Long id) {
        Optional<AcademicTitlesEntity> optionalAcademicTitles = academicTitlesRepository.findByIdAndDisplayTrue(id);
        if (optionalAcademicTitles.isPresent()) {
            AcademicTitlesEntity academicTitlesEntity = optionalAcademicTitles.get();
            academicTitlesMapper.updateEntity(academicTitlesEntity, academicTitlesUpdateRequest);
            return academicTitlesRepository.save(academicTitlesEntity);
        }
        return null;
    }
}
