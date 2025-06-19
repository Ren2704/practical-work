package com.example.demo.service;

import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectUpdateRequest;
import com.example.demo.entity.EducationSubjectEntity;

import java.util.List;

public interface EducationSubjectService {
    List<EducationSubjectEntity> findAll();
    EducationSubjectEntity create(EducationSubjectCreateRequest educationSubjectCreateRequest);
    EducationSubjectEntity update(EducationSubjectUpdateRequest educationSubjectUpdateRequest);
}
