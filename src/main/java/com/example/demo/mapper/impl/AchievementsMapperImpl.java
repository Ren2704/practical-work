package com.example.demo.mapper.impl;

import com.example.demo.controller.request.achievements.AchievementsCreateRequest;
import com.example.demo.entity.AchievementsEntity;
import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.demo.mapper.AchievementsMapper;
import com.example.demo.repository.OutstandingPeopleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class AchievementsMapperImpl implements AchievementsMapper {

    private final OutstandingPeopleRepository outstandingPeopleRepository;

    public AchievementsMapperImpl(OutstandingPeopleRepository outstandingPeopleRepository){
        this.outstandingPeopleRepository = outstandingPeopleRepository;
    }

    @Override
    public <T extends AchievementsCreateRequest> AchievementsEntity requestMapToAchievements(AchievementsEntity achievementsEntity, T request) {
        if (request == null)
            return null;
        achievementsEntity.setTitle(request.getTitle());
        achievementsEntity.setYear(request.getYear());
        achievementsEntity.setDescription(request.getDescription());

        Optional<OutstandingPeopleEntity> person = outstandingPeopleRepository.findById(request.getPersonId());
        if (person.isPresent())
            achievementsEntity.setPerson(person.get());

        return achievementsEntity;
    }
}
