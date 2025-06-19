package com.example.demo.mapper;

import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OutstandingPeopleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "academicTitles", ignore = true)
    @Mapping(target = "academicDegrees", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @Mapping(target = "display", constant = "true")
    OutstandingPeopleEntity requestMapToEntity(OutstandingPersonCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "academicTitles", ignore = true)
    @Mapping(target = "academicDegrees", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget OutstandingPeopleEntity entity, OutstandingPersonUpdateRequest request);
}
