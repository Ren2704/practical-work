package com.example.demo.dao.repository;

import com.example.demo.dao.entity.AcademicTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicTitleRepository extends JpaRepository<AcademicTitleEntity, Long> {
    List<AcademicTitleEntity> findByIsDeletedFalseOrderByNameAsc();
    List<AcademicTitleEntity> findByIsDeletedTrueOrderByNameAsc();
    Optional<AcademicTitleEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<AcademicTitleEntity> findByIdAndIsDeletedTrue(Long id);
    Optional<AcademicTitleEntity> findByNameIgnoreCaseAndIsDeletedFalse(String name);
    boolean existsByNameIgnoreCase(String name);

    @Modifying
    @Query("""
        DELETE FROM AcademicTitleEntity at
        WHERE at.deletedAt IS NOT NULL
        AND at.deletedAt < :threshold
    """)
    void deleteOldRecords(@Param("threshold") OffsetDateTime threshold);
}
