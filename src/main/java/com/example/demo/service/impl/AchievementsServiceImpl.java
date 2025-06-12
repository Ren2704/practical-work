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
        return achievementsRepository.findAll();
    }

    @Override
    public Optional<AchievementsEntity> findById(Long id) {
        return achievementsRepository.findById(id);
    }

    @Override
    public AchievementsEntity create(AchievementsCreateRequest achievementsCreateRequest) {
        AchievementsEntity achievementsEntity = new AchievementsEntity();
        achievementsEntity = achievementsMapper.requestMapToAchievements(achievementsEntity, achievementsCreateRequest);
        return achievementsRepository.save(achievementsEntity);
    }

    @Override
    public AchievementsEntity update(AchievementsUpdateRequest achievementsUpdateRequest) {
        Optional<AchievementsEntity> optionalAchievements = achievementsRepository.findById(achievementsUpdateRequest.getId());
        if (optionalAchievements.isPresent()) {
            AchievementsEntity achievementsEntity = optionalAchievements.get();
            achievementsEntity = achievementsMapper.requestMapToAchievements(achievementsEntity, achievementsUpdateRequest);
            return achievementsRepository.save(achievementsEntity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        achievementsRepository.deleteById(id);
    }
}
