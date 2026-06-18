package com.example.demo.dao.repository;

import com.example.demo.dao.entity.JobTitleEntity;
import com.example.demo.dao.entity.PersonJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JobTitleRepository extends JpaRepository<JobTitleEntity, Long> {
    List<JobTitleEntity> findByIsDeletedFalseOrderByNameAsc();
    List<JobTitleEntity> findByIsDeletedTrueOrderByNameAsc();
    Optional<JobTitleEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<JobTitleEntity> findByIdAndIsDeletedTrue(Long id);
    Optional<JobTitleEntity> findByNameIgnoreCaseAndIsDeletedFalse(String name);
    boolean existsByNameIgnoreCase(String name);

    @Modifying
    @Query("""
        DELETE FROM JobTitleEntity jt
        WHERE jt.deletedAt IS NOT NULL
        AND jt.deletedAt < :threshold
        AND NOT EXISTS (SELECT 1 FROM PersonJobEntity pj WHERE pj.jobTitle = jt)
    """)
    void deleteOldRecords(@Param("threshold") OffsetDateTime threshold);
}
