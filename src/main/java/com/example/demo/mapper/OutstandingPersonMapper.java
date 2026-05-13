package com.example.demo.mapper;

import com.example.demo.dao.entity.OutstandingPersonEntity;
import com.example.model.OutstandingPersonResponse;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonUpdateRequest;
import com.example.model.PersonResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {AchievementMapper.class, PersonJobMapper.class})
public interface OutstandingPersonMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @Mapping(target = "academicTitles", ignore = true)
    @Mapping(target = "academicDegrees", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    OutstandingPersonEntity toEntity(OutstandingPersonCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @Mapping(target = "academicTitles", ignore = true)
    @Mapping(target = "academicDegrees", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget OutstandingPersonEntity entity, OutstandingPersonUpdateRequest request);

    @Mapping(target = "academicDegree", source = "academicDegrees")
    @Mapping(target = "jobs", source = "personJob")
    OutstandingPersonResponse toResponse(OutstandingPersonEntity entity);

    PersonResponse toSimpleResponse(OutstandingPersonEntity entity);
}
