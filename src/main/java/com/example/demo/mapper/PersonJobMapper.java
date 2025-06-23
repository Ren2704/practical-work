package com.example.demo.mapper;

import com.example.demo.entity.PersonJobEntity;
import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobResponse;
import com.example.model.PersonJobUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = JobTitleMapper.class)
public interface PersonJobMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @Mapping(target = "display", constant = "true")
    PersonJobEntity requestMapToEntity(PersonJobCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget PersonJobEntity entity, PersonJobUpdateRequest request);

    PersonJobResponse entityMapToResponse(PersonJobEntity entity);
    List<PersonJobResponse> entityMapToResponseList(List<PersonJobEntity> entities);
}
