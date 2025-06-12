package com.example.demo.repository;

import com.example.demo.entity.AchievementsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementsRepository extends JpaRepository<AchievementsEntity, Long> {
}
