package com.example.demo.service;

import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementUpdateRequest;
import com.example.demo.entity.AchievementsEntity;

import java.util.List;

public interface AchievementsService {
    List<AchievementsEntity> findAll();
    List<AchievementsEntity> findByPersonId(Long id);
    AchievementsEntity create(AchievementCreateRequest achievementsCreateRequest);
    AchievementsEntity update(AchievementUpdateRequest achievementsUpdateRequest, Long id);
}
