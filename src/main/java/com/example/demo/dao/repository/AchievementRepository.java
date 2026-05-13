package com.example.demo.dao.repository;

import com.example.demo.dao.entity.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
    List<AchievementEntity> findByIsDeletedFalseOrderByYearDesc();
    List<AchievementEntity> findByIsDeletedTrueOrderByYearDesc();
    Optional<AchievementEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<AchievementEntity> findByIdAndIsDeletedTrue(Long id);
    List<AchievementEntity> findByPersonIdAndIsDeletedFalseOrderByYearDesc(Long personId);
}
