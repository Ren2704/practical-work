package com.example.demo.controller;

import com.example.api.AchievementControllerApi;
import com.example.demo.constants.Roles;
import com.example.demo.service.AchievementService;
import com.example.model.AchievementCreateRequest;
import com.example.model.AchievementResponse;
import com.example.model.AchievementUpdateRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AchievementControllerImpl implements AchievementControllerApi {

    private final AchievementService service;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<AchievementResponse> findAllAchievements() {
        return service.findAll();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public List<AchievementResponse> findAllDeletedAchievements() {
        return service.findAllDeleted();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public AchievementResponse findAchievementById(Long id) {
        return service.findById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AchievementResponse findDeletedAchievementById(Long id) {
        return service.findDeletedById(id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public List<AchievementResponse> findByPersonId(Long personId) {
        return service.findByPersonId(personId);
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AchievementResponse createAchievement(AchievementCreateRequest achievementCreateRequest) {
        return service.create(achievementCreateRequest);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AchievementResponse updateAchievement(Long id, AchievementUpdateRequest achievementUpdateRequest) {
        return service.update(achievementUpdateRequest, id);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed({Roles.ADMIN, Roles.EDITOR})
    public AchievementResponse recoverAchievement(Long id) {
        return service.recover(id);
    }
}
