package com.example.demo.service.impl;

import com.example.demo.controller.request.academic.titles.AcademicTitlesCreateRequest;
import com.example.demo.controller.request.academic.titles.AcademicTitlesUpdateRequest;
import com.example.demo.entity.AcademicTitlesEntity;
import com.example.demo.mapper.AcademicTitlesMapper;
import com.example.demo.repository.AcademicTitlesRepository;
import com.example.demo.service.AcademicTitlesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicTitlesServiceImpl implements AcademicTitlesService {

    private final AcademicTitlesRepository academicTitlesRepository;
    private final AcademicTitlesMapper academicTitlesMapper;

    public AcademicTitlesServiceImpl(AcademicTitlesRepository academicTitlesRepository, AcademicTitlesMapper academicTitlesMapper) {
        this.academicTitlesRepository = academicTitlesRepository;
        this.academicTitlesMapper = academicTitlesMapper;
    }

    @Override
    public List<AcademicTitlesEntity> findAll() {
        return academicTitlesRepository.findAll();
    }

    @Override
    public Optional<AcademicTitlesEntity> findById(Long id) {
        return academicTitlesRepository.findById(id);
    }

    @Override
    public AcademicTitlesEntity create(AcademicTitlesCreateRequest academicTitlesCreateRequest) {
        AcademicTitlesEntity academicTitlesEntity = new AcademicTitlesEntity();
        academicTitlesEntity = academicTitlesMapper.requestMapToAcademicTitles(academicTitlesEntity, academicTitlesCreateRequest);
        return academicTitlesRepository.save(academicTitlesEntity);
    }

    @Override
    public AcademicTitlesEntity update(AcademicTitlesUpdateRequest academicTitlesUpdateRequest) {
        Optional<AcademicTitlesEntity> optionalAcademicTitles = academicTitlesRepository.findById(academicTitlesUpdateRequest.getId());
        if (optionalAcademicTitles.isPresent()) {
            AcademicTitlesEntity academicTitlesEntity = optionalAcademicTitles.get();
            academicTitlesEntity = academicTitlesMapper.requestMapToAcademicTitles(academicTitlesEntity, academicTitlesUpdateRequest);
            return academicTitlesRepository.save(academicTitlesEntity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        academicTitlesRepository.deleteById(id);
    }
}
