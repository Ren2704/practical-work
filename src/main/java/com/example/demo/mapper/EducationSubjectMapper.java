package com.example.demo.mapper;

import com.example.demo.controller.request.education.subject.EducationSubjectCreateRequest;
import com.example.demo.entity.EducationSubjectEntity;

public interface EducationSubjectMapper {
    <T extends EducationSubjectCreateRequest> EducationSubjectEntity requestMapToEducationSubject(EducationSubjectEntity educationSubjectEntity, T request);
}
