package com.example.demo.mapper;

import com.example.demo.dao.entity.JobTitleEntity;
import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleResponse;
import com.example.model.JobTitleUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface JobTitleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    JobTitleEntity toEntity(JobTitleCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "personJob", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget JobTitleEntity entity, JobTitleUpdateRequest request);

    JobTitleResponse toResponse(JobTitleEntity entity);
}
