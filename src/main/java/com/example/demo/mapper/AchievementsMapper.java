package com.example.demo.mapper;

import com.example.demo.entity.AchievementsEntity;
import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementResponse;
import com.example.model.AchievementUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AchievementsMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @Mapping(target = "display", constant = "true")
    AchievementsEntity requestMapToEntity(AchievementCreateRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "person", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget AchievementsEntity entity, AchievementUpdateRequest request);

    AchievementResponse entityMapToResponse(AchievementsEntity entity);
    List<AchievementResponse> entityMapToResponseList(List<AchievementsEntity> entities);
}
