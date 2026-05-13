package com.example.demo.service;

import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectResponse;
import com.example.model.EducationSubjectUpdateRequest;

import java.util.List;

public interface EducationSubjectService {
    List<EducationSubjectResponse> findAll();
    List<EducationSubjectResponse> findAllDeleted();
    EducationSubjectResponse findById(Long id);
    EducationSubjectResponse findDeletedById(Long id);
    EducationSubjectResponse create(EducationSubjectCreateRequest educationSubjectCreateRequest);
    EducationSubjectResponse update(EducationSubjectUpdateRequest educationSubjectUpdateRequest, Long id);
    EducationSubjectResponse recover (Long id);
}
