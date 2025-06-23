package com.example.demo.mapper;

import com.example.demo.entity.EducationSubjectEntity;
import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectResponse;
import com.example.model.EducationSubjectUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EducationSubjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "display", constant = "true")
    EducationSubjectEntity requestMapToEntity(EducationSubjectCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget EducationSubjectEntity entity, EducationSubjectUpdateRequest request);

    EducationSubjectResponse entityMapToResponse(EducationSubjectEntity entity);
    List<EducationSubjectResponse> entityMapToResponseList(List<EducationSubjectEntity> entities);
}
