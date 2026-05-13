package com.example.demo.service.impl;

import com.example.demo.dao.entity.OutstandingPersonEntity;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementResponse;
import com.example.model.AchievementUpdateRequest;
import com.example.demo.dao.entity.AchievementEntity;
import com.example.demo.mapper.AchievementMapper;
import com.example.demo.dao.repository.AchievementRepository;
import com.example.demo.dao.repository.OutstandingPersonRepository;
import com.example.demo.service.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository repository;
    private final AchievementMapper mapper;
    private final OutstandingPersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AchievementResponse> findAll() {
        List<AchievementEntity> achievementEntity = repository.findByIsDeletedFalseOrderByYearDesc();
        return achievementEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AchievementResponse> findAllDeleted() {
        List<AchievementEntity> achievementEntity = repository.findByIsDeletedTrueOrderByYearDesc();
        return achievementEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public AchievementResponse findById(Long id) {
        AchievementEntity achievementEntity =  findAchievementById(id);
        return mapper.toResponse(achievementEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public AchievementResponse findDeletedById(Long id) {
        AchievementEntity achievementEntity = findDeletedAchievementById(id);
        return mapper.toResponse(achievementEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AchievementResponse> findByPersonId(Long id) {
        List<AchievementEntity> achievementEntity = repository.findByPersonIdAndIsDeletedFalseOrderByYearDesc(id);
        return achievementEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional()
    public AchievementResponse create(AchievementCreateRequest achievementCreateRequest) {
        AchievementEntity achievementEntity = mapper.toEntity(achievementCreateRequest);
        setPersonFromRequest(achievementEntity, achievementCreateRequest.getPersonId());
        repository.save(achievementEntity);
        return mapper.toResponse(achievementEntity);
    }

    @Override
    @Transactional
    public AchievementResponse update(AchievementUpdateRequest achievementsUpdateRequest, Long id) {
        AchievementEntity achievementEntity = findAchievementById(id);
        mapper.updateEntity(achievementEntity, achievementsUpdateRequest);
        setPersonFromRequest(achievementEntity, achievementsUpdateRequest.getPersonId());
        return mapper.toResponse(achievementEntity);
    }

    @Override
    @Transactional
    public AchievementResponse recover(Long id) {
        AchievementEntity achievementEntity = findDeletedAchievementById(id);
        achievementEntity.setIsDeleted(false);
        return mapper.toResponse(achievementEntity);
    }

    private AchievementEntity findAchievementById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("Achievement not found with id: " + id)
        );
    }

    private AchievementEntity findDeletedAchievementById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException("Deleted achievement not found with id: " + id)
        );
    }

    private void setPersonFromRequest(AchievementEntity entity, Long personId) {
        OutstandingPersonEntity person = personRepository.findByIdAndIsDeletedFalse(personId).orElseThrow(
                () -> new NotFoundException("Person not found with id: " + personId)
        );
        entity.setPerson(person);
    }
}
