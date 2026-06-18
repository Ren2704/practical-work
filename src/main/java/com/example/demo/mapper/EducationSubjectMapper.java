package com.example.demo.mapper;

import com.example.demo.dao.entity.EducationSubjectEntity;
import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectResponse;
import com.example.model.EducationSubjectUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EducationSubjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "deletedAt", ignore = true)
    EducationSubjectEntity toEntity(EducationSubjectCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget EducationSubjectEntity entity, EducationSubjectUpdateRequest request);

    EducationSubjectResponse toResponse(EducationSubjectEntity entity);
}
