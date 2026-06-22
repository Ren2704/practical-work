package com.example.demo.dao.repository;

import com.example.demo.dao.entity.PersonJobEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonJobRepository extends JpaRepository<PersonJobEntity, Long> {
    @EntityGraph(attributePaths = {"jobTitle"})
    List<PersonJobEntity> findByIsDeletedFalse();
    @EntityGraph(attributePaths = {"jobTitle"})
    List<PersonJobEntity> findByIsDeletedTrue();
    @EntityGraph(attributePaths = {"jobTitle"})
    Optional<PersonJobEntity> findByIdAndIsDeletedFalse(Long id);
    @EntityGraph(attributePaths = {"jobTitle"})
    Optional<PersonJobEntity> findByIdAndIsDeletedTrue(Long id);
    @EntityGraph(attributePaths = {"jobTitle"})
    List<PersonJobEntity> findByPersonIdAndIsDeletedFalseOrderByStartYearDesc(Long personId);
    @EntityGraph(attributePaths = {"jobTitle"})
    Optional<PersonJobEntity> findByPersonIdAndCurrentTrueAndIsDeletedFalse(Long personId);

    @Modifying
    @Query("""
        DELETE FROM PersonJobEntity pj
        WHERE pj.deletedAt IS NOT NULL
        AND pj.deletedAt < :threshold
    """)
    void deleteOldRecords(@Param("threshold") OffsetDateTime threshold);
}
