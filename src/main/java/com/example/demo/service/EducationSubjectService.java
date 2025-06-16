package com.example.demo.service;

import com.example.demo.controller.request.education.subject.EducationSubjectCreateRequest;
import com.example.demo.controller.request.education.subject.EducationSubjectUpdateRequest;
import com.example.demo.entity.EducationSubjectEntity;

import java.util.List;
import java.util.Optional;

public interface EducationSubjectService {
    List<EducationSubjectEntity> findAll();
    EducationSubjectEntity create(EducationSubjectCreateRequest educationSubjectCreateRequest);
    EducationSubjectEntity update(EducationSubjectUpdateRequest educationSubjectUpdateRequest);
}
