package com.example.demo.service;

import com.example.model.AcademicDegreeCreateRequest;
import com.example.model.AcademicDegreeResponse;
import com.example.model.AcademicDegreeUpdateRequest;

import java.util.List;

public interface AcademicDegreeService {
    List<AcademicDegreeResponse> findAll();
    List<AcademicDegreeResponse> findAllDeleted();
    AcademicDegreeResponse findById(Long id);
    AcademicDegreeResponse findDeletedById(Long id);
    AcademicDegreeResponse create(AcademicDegreeCreateRequest academicDegreeCreateRequest);
    AcademicDegreeResponse update(AcademicDegreeUpdateRequest academicDegreeUpdateRequest, Long id);
    AcademicDegreeResponse recover (Long id);
}
