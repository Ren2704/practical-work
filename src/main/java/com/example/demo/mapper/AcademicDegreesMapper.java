package com.example.demo.mapper;

import com.example.model.AcademicDegreesResponse;
import com.example.model.AcademicDegreesUpdateRequest;
import org.mapstruct.*;

import com.example.demo.entity.AcademicDegreesEntity;
import com.example.model.AcademicDegreesCreateRequest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademicDegreesMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "display", constant = "true")
    AcademicDegreesEntity requestMapToEntity(AcademicDegreesCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget AcademicDegreesEntity entity, AcademicDegreesUpdateRequest request);

    AcademicDegreesResponse entityMapToResponse(AcademicDegreesEntity entity);
    List<AcademicDegreesResponse> entityMapToResponseList(List<AcademicDegreesEntity> entities);
}
