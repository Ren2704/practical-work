package com.example.demo.mapper.impl;

import com.example.demo.controller.request.academic.titles.AcademicTitlesCreateRequest;
import com.example.demo.entity.AcademicTitlesEntity;
import com.example.demo.mapper.AcademicTitlesMapper;
import org.springframework.stereotype.Component;


@Component
public class AcademicTitlesMapperImpl implements AcademicTitlesMapper {
    @Override
    public <T extends AcademicTitlesCreateRequest> AcademicTitlesEntity requestMapToAcademicTitles(AcademicTitlesEntity academicTitlesEntity, T request) {
        if (request == null)
            return null;
        academicTitlesEntity.setName(request.getName());
        academicTitlesEntity.setShortName(request.getShortName());

        return academicTitlesEntity;
    }
}
