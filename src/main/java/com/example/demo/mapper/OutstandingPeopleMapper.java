package com.example.demo.mapper;

import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.model.OutstandingPeopleResponse;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonResponse;
import com.example.model.OutstandingPersonUpdateRequest;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AchievementsMapper.class, PersonJobMapper.class})
public interface OutstandingPeopleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @Mapping(target = "academicTitles", ignore = true)
    @Mapping(target = "academicDegrees", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @Mapping(target = "display", constant = "true")
    OutstandingPeopleEntity requestMapToEntity(OutstandingPersonCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "achievements", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @Mapping(target = "academicTitles", ignore = true)
    @Mapping(target = "academicDegrees", ignore = true)
    @Mapping(target = "educationSubject", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget OutstandingPeopleEntity entity, OutstandingPersonUpdateRequest request);

    @Mapping(target = "academicDegree", source = "academicDegrees")
    @Mapping(target = "jobs", source = "personJob")
    OutstandingPersonResponse entityMapToResponse(OutstandingPeopleEntity entity);

    List<OutstandingPeopleResponse> entityMapToResponseList(List<OutstandingPeopleEntity> entities);
}
