package com.example.demo.service;

import com.example.demo.controller.request.academic.degrees.AcademicDegreesCreateRequest;
import com.example.demo.controller.request.academic.degrees.AcademicDegreesUpdateRequest;
import com.example.demo.entity.AcademicDegreesEntity;

import java.util.List;
import java.util.Optional;

public interface AcademicDegreesService {
    List<AcademicDegreesEntity> findAll();
    AcademicDegreesEntity create(AcademicDegreesCreateRequest academicDegreesCreateRequest);
    AcademicDegreesEntity update(AcademicDegreesUpdateRequest academicDegreesUpdateRequest);

}
