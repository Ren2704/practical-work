package com.example.demo.mapper.impl;

import com.example.demo.controller.request.education.subject.EducationSubjectCreateRequest;
import com.example.demo.entity.EducationSubjectEntity;
import com.example.demo.mapper.EducationSubjectMapper;
import org.springframework.stereotype.Component;


@Component
public class EducationSubjectMapperImpl implements EducationSubjectMapper {
    @Override
    public <T extends EducationSubjectCreateRequest> EducationSubjectEntity requestMapToEducationSubject(EducationSubjectEntity educationSubject, T request) {
        if (request == null)
            return null;
        educationSubject.setName(request.getName());
        educationSubject.setShortName(request.getShortName());

        return educationSubject;
    }
}
