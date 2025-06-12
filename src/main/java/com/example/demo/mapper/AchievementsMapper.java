package com.example.demo.mapper;

import com.example.demo.controller.request.achievements.AchievementsCreateRequest;
import com.example.demo.entity.AchievementsEntity;

public interface AchievementsMapper {
    <T extends AchievementsCreateRequest> AchievementsEntity requestMapToAchievements(AchievementsEntity achievementsEntity, T request);
}
