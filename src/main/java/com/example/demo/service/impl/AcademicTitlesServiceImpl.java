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
        return academicTitlesRepository.findByDisplayTrueOrderByNameAsc();
    }

    @Override
    public AcademicTitlesEntity create(AcademicTitlesCreateRequest academicTitlesCreateRequest) {
        AcademicTitlesEntity academicTitles = new AcademicTitlesEntity();
        academicTitles = academicTitlesMapper.requestMapToAcademicTitles(academicTitles, academicTitlesCreateRequest);
        return academicTitlesRepository.save(academicTitles);
    }

    @Override
    public AcademicTitlesEntity update(AcademicTitlesUpdateRequest academicTitlesUpdateRequest) {
        Optional<AcademicTitlesEntity> optionalAcademicTitles = academicTitlesRepository.findByIdAndDisplayTrue(academicTitlesUpdateRequest.getId());
        if (optionalAcademicTitles.isPresent()) {
            AcademicTitlesEntity academicTitles = optionalAcademicTitles.get();
            academicTitles = academicTitlesMapper.requestMapToAcademicTitles(academicTitles, academicTitlesUpdateRequest);
            return academicTitlesRepository.save(academicTitles);
        }
        return null;
    }
}
