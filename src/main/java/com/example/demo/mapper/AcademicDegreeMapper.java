package com.example.demo.mapper;

import com.example.model.AcademicDegreeResponse;
import com.example.model.AcademicDegreeUpdateRequest;
import org.mapstruct.*;

import com.example.demo.dao.entity.AcademicDegreeEntity;
import com.example.model.AcademicDegreeCreateRequest;

@Mapper(componentModel = "spring")
public interface AcademicDegreeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "deletedAt", ignore = true)
    AcademicDegreeEntity toEntity(AcademicDegreeCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget AcademicDegreeEntity entity, AcademicDegreeUpdateRequest request);

    AcademicDegreeResponse toResponse(AcademicDegreeEntity entity);
}
