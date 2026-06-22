package com.example.demo.mapper;

import com.example.demo.dao.entity.PersonJobEntity;
import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobResponse;
import com.example.model.PersonJobUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = JobTitleMapper.class)
public interface PersonJobMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "deletedAt", ignore = true)
    PersonJobEntity toEntity(PersonJobCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "jobTitle", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget PersonJobEntity entity, PersonJobUpdateRequest request);

    PersonJobResponse toResponse(PersonJobEntity entity);
}
