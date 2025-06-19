package com.example.demo.service;

import com.example.model.AcademicTitlesCreateRequest;
import com.example.model.AcademicTitlesUpdateRequest;
import com.example.demo.entity.AcademicTitlesEntity;

import java.util.List;

public interface AcademicTitlesService {
    List<AcademicTitlesEntity> findAll();
    AcademicTitlesEntity create(AcademicTitlesCreateRequest academicTitlesCreateRequest);
    AcademicTitlesEntity update(AcademicTitlesUpdateRequest academicTitlesUpdateRequest);

}
