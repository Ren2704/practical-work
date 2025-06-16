package com.example.demo.service.impl;

import com.example.demo.controller.request.achievements.AchievementsCreateRequest;
import com.example.demo.controller.request.achievements.AchievementsUpdateRequest;
import com.example.demo.entity.AchievementsEntity;
import com.example.demo.mapper.AchievementsMapper;
import com.example.demo.repository.AchievementsRepository;
import com.example.demo.service.AchievementsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementsServiceImpl implements AchievementsService {

    private final AchievementsRepository achievementsRepository;
    private final AchievementsMapper achievementsMapper;

    public AchievementsServiceImpl(AchievementsRepository achievementsRepository, AchievementsMapper achievementsMapper) {
        this.achievementsRepository = achievementsRepository;
        this.achievementsMapper = achievementsMapper;
    }

    @Override
    public List<AchievementsEntity> findAll() {
        return achievementsRepository.findByDisplayTrueOrderByYearDesc();
    }

    @Override
    public List<AchievementsEntity> findByPersonId(Long id) {
        return achievementsRepository.findByPersonIdAndDisplayTrueOrderByYearDesc(id);
    }

    @Override
    public AchievementsEntity create(AchievementsCreateRequest achievementsCreateRequest) {
        AchievementsEntity achievements = new AchievementsEntity();
        achievements = achievementsMapper.requestMapToAchievements(achievements, achievementsCreateRequest);
        return achievementsRepository.save(achievements);
    }

    @Override
    public AchievementsEntity update(AchievementsUpdateRequest achievementsUpdateRequest) {
        Optional<AchievementsEntity> optionalAchievements = achievementsRepository.findByIdAndDisplayTrue(achievementsUpdateRequest.getId());
        if (optionalAchievements.isPresent()) {
            AchievementsEntity achievements = optionalAchievements.get();
            achievements = achievementsMapper.requestMapToAchievements(achievements, achievementsUpdateRequest);
            return achievementsRepository.save(achievements);
        }
        return null;
    }
}
