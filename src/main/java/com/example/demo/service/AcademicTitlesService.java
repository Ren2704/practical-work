package com.example.demo.service;

import com.example.demo.controller.request.academic.titles.AcademicTitlesCreateRequest;
import com.example.demo.controller.request.academic.titles.AcademicTitlesUpdateRequest;
import com.example.demo.entity.AcademicTitlesEntity;

import java.util.List;
import java.util.Optional;

public interface AcademicTitlesService {
    List<AcademicTitlesEntity> findAll();
    Optional<AcademicTitlesEntity> findById(Long id);
    AcademicTitlesEntity create(AcademicTitlesCreateRequest academicTitlesCreateRequest);
    AcademicTitlesEntity update(AcademicTitlesUpdateRequest academicTitlesUpdateRequest);
    void delete(Long id);
}
