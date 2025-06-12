package com.example.demo.service;

import com.example.demo.controller.request.academic.degrees.AcademicDegreesCreateRequest;
import com.example.demo.controller.request.academic.degrees.AcademicDegreesUpdateRequest;
import com.example.demo.entity.AcademicDegreesEntity;

import java.util.List;
import java.util.Optional;

public interface AcademicDegreesService {
    List<AcademicDegreesEntity> findAll();
    Optional<AcademicDegreesEntity> findById(Long id);
    AcademicDegreesEntity create(AcademicDegreesCreateRequest academicDegreesCreateRequest);
    AcademicDegreesEntity update(AcademicDegreesUpdateRequest academicDegreesUpdateRequest);
    void delete(Long id);
}
