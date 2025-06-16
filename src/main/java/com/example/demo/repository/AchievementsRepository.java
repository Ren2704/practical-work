package com.example.demo.repository;

import com.example.demo.entity.AchievementsEntity;
import com.example.demo.entity.PersonJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AchievementsRepository extends JpaRepository<AchievementsEntity, Long> {
    List<AchievementsEntity> findByPersonIdAndDisplayTrueOrderByYearDesc(Long personId);
    Optional<AchievementsEntity> findByIdAndDisplayTrue(Long id);
    List<AchievementsEntity> findByDisplayTrueOrderByYearDesc();
}
