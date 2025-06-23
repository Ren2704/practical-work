package com.example.demo.service.impl;

import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementUpdateRequest;
import com.example.demo.entity.AchievementsEntity;
import com.example.demo.mapper.AchievementsMapper;
import com.example.demo.repository.AchievementsRepository;
import com.example.demo.repository.OutstandingPeopleRepository;
import com.example.demo.service.AchievementsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementsServiceImpl implements AchievementsService {

    private final AchievementsRepository achievementsRepository;
    private final AchievementsMapper achievementsMapper;
    private final OutstandingPeopleRepository outstandingPeopleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AchievementsEntity> findAll() {
        return achievementsRepository.findByDisplayTrueOrderByYearDesc();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AchievementsEntity> findByPersonId(Long id) {
        return achievementsRepository.findByPersonIdAndDisplayTrueOrderByYearDesc(id);
    }

    @Override
    @Transactional
    public AchievementsEntity create(AchievementCreateRequest achievementCreateRequest) {
        AchievementsEntity achievementsEntity = achievementsMapper.requestMapToEntity(achievementCreateRequest);
        setPersonFromRequest(achievementsEntity, achievementCreateRequest.getPersonId());
        return achievementsRepository.save(achievementsEntity);
    }

    @Override
    @Transactional
    public AchievementsEntity update(AchievementUpdateRequest achievementsUpdateRequest, Long id) {
        Optional<AchievementsEntity> optionalAchievements = achievementsRepository.findByIdAndDisplayTrue(id);
        if (optionalAchievements.isPresent()) {
            AchievementsEntity achievementsEntity = optionalAchievements.get();
            achievementsMapper.updateEntity(achievementsEntity, achievementsUpdateRequest);
            if (achievementsUpdateRequest.getPersonId() != null) {
                setPersonFromRequest(achievementsEntity, achievementsUpdateRequest.getPersonId());
            }
            return achievementsRepository.save(achievementsEntity);
        }
        return null;
    }


    private void setPersonFromRequest(AchievementsEntity entity, Long personId) {
        Optional<OutstandingPeopleEntity> person = outstandingPeopleRepository.findByIdAndDisplayTrue(personId);
        if (person.isPresent())
            entity.setPerson(person.get());
    }
}
