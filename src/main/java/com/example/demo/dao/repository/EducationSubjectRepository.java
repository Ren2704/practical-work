package com.example.demo.dao.repository;

import com.example.demo.dao.entity.EducationSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EducationSubjectRepository extends JpaRepository<EducationSubjectEntity, Long> {
    List<EducationSubjectEntity> findByIsDeletedFalseOrderByNameAsc();
    List<EducationSubjectEntity> findByIsDeletedTrueOrderByNameAsc();
    Optional<EducationSubjectEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<EducationSubjectEntity> findByIdAndIsDeletedTrue(Long id);
    Optional<EducationSubjectEntity> findByNameIgnoreCaseAndIsDeletedFalse(String name);
    boolean existsByNameIgnoreCase(String name);

    @Modifying
    @Query("""
        DELETE FROM EducationSubjectEntity es
        WHERE es.deletedAt IS NOT NULL
        AND es.deletedAt < :threshold
    """)
    void deleteOldRecords(@Param("threshold") OffsetDateTime threshold);
}
