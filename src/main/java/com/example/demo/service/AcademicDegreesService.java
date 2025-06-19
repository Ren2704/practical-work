package com.example.demo.service;

import com.example.model.AcademicDegreesCreateRequest;
import com.example.model.AcademicDegreesUpdateRequest;
import com.example.demo.entity.AcademicDegreesEntity;

import java.util.List;

public interface AcademicDegreesService {
    List<AcademicDegreesEntity> findAll();
    AcademicDegreesEntity create(AcademicDegreesCreateRequest academicDegreesCreateRequest);
    AcademicDegreesEntity update(AcademicDegreesUpdateRequest academicDegreesUpdateRequest);

}
