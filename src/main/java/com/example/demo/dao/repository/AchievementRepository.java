package com.example.demo.dao.repository;

import com.example.demo.dao.entity.AchievementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, Long> {
    @Query("""
    SELECT a
    FROM AchievementEntity a
    LEFT JOIN FETCH a.person
    WHERE a.id = :id AND a.isDeleted = false
    """)
    Optional<AchievementEntity> findByIdAndIsDeletedFalse(@Param("id") Long id);

    @Query("""
    SELECT a
    FROM AchievementEntity a
    LEFT JOIN FETCH a.person
    WHERE a.id = :id AND a.isDeleted = true
    """)
    Optional<AchievementEntity> findByIdAndIsDeletedTrue(@Param("id") Long id);

    @Query("""
        SELECT a
        FROM AchievementEntity a
        WHERE a.person.id = :personId
        AND a.isDeleted = false
        ORDER BY a.year DESC
    """)
    List<AchievementEntity> findAchievementByPersonId(@Param("personId") Long personId);

    @Modifying
    @Query("""
        DELETE FROM AchievementEntity a
        WHERE a.deletedAt IS NOT NULL
        AND a.deletedAt < :threshold
    """)
    void deleteOldRecords(@Param("threshold") OffsetDateTime threshold);

    List<AchievementEntity> findByIsDeletedFalseOrderByYearDesc();
    List<AchievementEntity> findByIsDeletedTrueOrderByYearDesc();
}