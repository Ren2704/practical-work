package com.example.demo.mapper;

import com.example.demo.entity.AcademicTitlesEntity;
import com.example.model.AcademicTitlesCreateRequest;
import com.example.model.AcademicTitlesUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AcademicTitlesMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "display", constant = "true")
    AcademicTitlesEntity requestMapToEntity (AcademicTitlesCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget AcademicTitlesEntity entity, AcademicTitlesUpdateRequest request);
}
