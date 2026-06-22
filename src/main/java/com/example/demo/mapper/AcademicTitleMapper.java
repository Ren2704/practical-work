package com.example.demo.mapper;

import com.example.demo.dao.entity.AcademicTitleEntity;
import com.example.model.AcademicTitleCreateRequest;
import com.example.model.AcademicTitleResponse;
import com.example.model.AcademicTitleUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AcademicTitleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "deletedAt", ignore = true)
    AcademicTitleEntity toEntity (AcademicTitleCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget AcademicTitleEntity entity, AcademicTitleUpdateRequest request);

    AcademicTitleResponse toResponse(AcademicTitleEntity entity);
}
