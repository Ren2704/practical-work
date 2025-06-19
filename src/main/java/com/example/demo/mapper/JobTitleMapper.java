package com.example.demo.mapper;

import com.example.demo.entity.JobTitleEntity;
import com.example.model.JobTitleCreateRequest;
import com.example.model.JobTitleUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface JobTitleMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "display", constant = "true")
    JobTitleEntity requestMapToEntity(JobTitleCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget JobTitleEntity entity, JobTitleUpdateRequest request);
}
