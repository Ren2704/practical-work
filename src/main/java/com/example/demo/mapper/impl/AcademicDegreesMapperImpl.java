package com.example.demo.mapper.impl;

import com.example.demo.controller.request.academic.degrees.AcademicDegreesCreateRequest;
import com.example.demo.entity.AcademicDegreesEntity;
import com.example.demo.mapper.AcademicDegreesMapper;
import org.springframework.stereotype.Component;


@Component
public class AcademicDegreesMapperImpl implements AcademicDegreesMapper {
    @Override
    public <T extends AcademicDegreesCreateRequest> AcademicDegreesEntity requestMapToAcademicDegrees(AcademicDegreesEntity academicDegrees, T request) {
        if (request == null)
            return null;
        academicDegrees.setName(request.getName());
        academicDegrees.setShortName(request.getShortName());

        return academicDegrees;
    }
}
