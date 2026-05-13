package com.example.demo.service;

import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementResponse;
import com.example.model.AchievementUpdateRequest;

import java.util.List;

public interface AchievementService {
    List<AchievementResponse> findAll();
    List<AchievementResponse> findAllDeleted();
    AchievementResponse findById(Long id);
    AchievementResponse findDeletedById(Long id);
    List<AchievementResponse> findByPersonId(Long id);
    AchievementResponse create(AchievementCreateRequest achievementCreateRequest);
    AchievementResponse update(AchievementUpdateRequest achievementUpdateRequest, Long id);
    AchievementResponse recover (Long id);
}
