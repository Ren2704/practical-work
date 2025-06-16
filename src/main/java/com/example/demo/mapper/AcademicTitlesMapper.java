package com.example.demo.mapper;

import com.example.demo.controller.request.academic.titles.AcademicTitlesCreateRequest;
import com.example.demo.entity.AcademicTitlesEntity;

public interface AcademicTitlesMapper {
    <T extends AcademicTitlesCreateRequest> AcademicTitlesEntity requestMapToAcademicTitles(AcademicTitlesEntity academicTitles, T request);
}
