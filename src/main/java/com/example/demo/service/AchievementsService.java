package com.example.demo.service;

import com.example.demo.controller.request.achievements.AchievementsCreateRequest;
import com.example.demo.controller.request.achievements.AchievementsUpdateRequest;
import com.example.demo.entity.AchievementsEntity;

import java.util.List;
import java.util.Optional;

public interface AchievementsService {
    List<AchievementsEntity> findAll();
    List<AchievementsEntity> findByPersonId(Long id);
    AchievementsEntity create(AchievementsCreateRequest achievementsCreateRequest);
    AchievementsEntity update(AchievementsUpdateRequest achievementsUpdateRequest);
}
