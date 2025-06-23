package com.example.demo.controller;

import com.example.api.AchievementsControllerApi;
import com.example.demo.entity.AchievementsEntity;
import com.example.demo.mapper.AchievementsMapper;
import com.example.demo.service.AchievementsService;
import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementResponse;
import com.example.model.AchievementUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AchievementsControllerImpl implements AchievementsControllerApi {

    private final AchievementsService achievementsService;
    private final AchievementsMapper achievementsMapper;
    @Override
    public ResponseEntity<AchievementResponse> createAchievement(AchievementCreateRequest achievementCreateRequest) {
        AchievementsEntity achievementsEntity = achievementsService.create(achievementCreateRequest);
        return new ResponseEntity<>(achievementsMapper.entityMapToResponse(achievementsEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<AchievementResponse>> findAllAchievements() {
        List<AchievementsEntity> achievementsEntity = achievementsService.findAll();
        List<AchievementResponse> responseList = achievementsMapper.entityMapToResponseList(achievementsEntity);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AchievementResponse>> findByPersonId(Long personId) {
        List<AchievementsEntity> achievementsEntities = achievementsService.findByPersonId(personId);
        if (achievementsEntities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<AchievementResponse> responseList = achievementsMapper.entityMapToResponseList(achievementsEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AchievementResponse> updateAchievement(Long id, AchievementUpdateRequest achievementUpdateRequest) {
        AchievementsEntity achievementsEntity = achievementsService.update(achievementUpdateRequest, id);
        return new ResponseEntity<>(achievementsMapper.entityMapToResponse(achievementsEntity), HttpStatus.OK);
    }
}
