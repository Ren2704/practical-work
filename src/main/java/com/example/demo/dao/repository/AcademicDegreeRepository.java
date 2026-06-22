package com.example.demo.dao.repository;

import com.example.demo.dao.entity.AcademicDegreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicDegreeRepository extends JpaRepository<AcademicDegreeEntity, Long> {
    List<AcademicDegreeEntity> findByIsDeletedFalseOrderByNameAsc();
    List<AcademicDegreeEntity> findByIsDeletedTrueOrderByNameAsc();
    Optional<AcademicDegreeEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<AcademicDegreeEntity> findByIdAndIsDeletedTrue(Long id);
    Optional<AcademicDegreeEntity> findByNameIgnoreCaseAndIsDeletedFalse(String name);
    boolean existsByNameIgnoreCase(String name);

    @Modifying
    @Query("""
        DELETE FROM AcademicDegreeEntity ad
        WHERE ad.deletedAt IS NOT NULL
        AND ad.deletedAt < :threshold
    """)
    void deleteOldRecords(@Param("threshold") OffsetDateTime threshold);
}
