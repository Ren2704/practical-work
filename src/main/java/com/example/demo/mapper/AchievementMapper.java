package com.example.demo.mapper;

import com.example.demo.dao.entity.AchievementEntity;
import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementResponse;
import com.example.model.AchievementUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AchievementMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "deletedAt", ignore = true)
    AchievementEntity toEntity(AchievementCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    void updateEntity(@MappingTarget AchievementEntity entity, AchievementUpdateRequest request);

    AchievementResponse toResponse(AchievementEntity entity);
}
