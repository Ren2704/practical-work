package com.example.demo.mapper;


import com.example.demo.controller.request.academic.degrees.AcademicDegreesCreateRequest;
import com.example.demo.entity.AcademicDegreesEntity;

public interface AcademicDegreesMapper {
    <T extends AcademicDegreesCreateRequest> AcademicDegreesEntity requestMapToAcademicDegrees(AcademicDegreesEntity academicDegreesEntity, T request);
}
