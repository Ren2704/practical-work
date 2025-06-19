package com.example.demo.mapper;

import com.example.demo.entity.EducationSubjectEntity;
import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface EducationSubjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "display", constant = "true")
    EducationSubjectEntity requestMapToEntity(EducationSubjectCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget EducationSubjectEntity entity, EducationSubjectUpdateRequest request);
}
