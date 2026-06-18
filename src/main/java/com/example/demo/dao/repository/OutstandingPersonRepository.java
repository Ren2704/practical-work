package com.example.demo.dao.repository;

import com.example.demo.dao.entity.OutstandingPersonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.List;

@Repository
public interface OutstandingPersonRepository extends JpaRepository<OutstandingPersonEntity, Long> {
    @Query("""
    SELECT p
    FROM OutstandingPersonEntity p
    LEFT JOIN FETCH p.academicTitle
    LEFT JOIN FETCH p.academicDegree
    LEFT JOIN FETCH p.educationSubject
    WHERE p.id = :id AND p.isDeleted = false
    """)
    Optional<OutstandingPersonEntity> findByIdAndIsDeletedFalse(@Param("id") Long id);

    @Query("""
    SELECT p
    FROM OutstandingPersonEntity p
    LEFT JOIN FETCH p.academicTitle
    LEFT JOIN FETCH p.academicDegree
    LEFT JOIN FETCH p.educationSubject
    WHERE p.id = :id AND p.isDeleted = true
    """)
    Optional<OutstandingPersonEntity> findByIdAndIsDeletedTrue(@Param("id") Long id);

    @Modifying
    @Query("""
        DELETE FROM OutstandingPersonEntity p
        WHERE p.deletedAt IS NOT NULL
        AND p.deletedAt < :threshold
    """)
    void deleteOldRecords(@Param("threshold") OffsetDateTime threshold);

    Slice<OutstandingPersonEntity> findByIsDeletedFalseOrderBySurnameAsc(Pageable pageable);
    Slice<OutstandingPersonEntity> findByIsDeletedTrueOrderBySurnameAsc(Pageable pageable);
    Slice<OutstandingPersonEntity> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name, Pageable pageable);
    Slice<OutstandingPersonEntity> findBySurnameContainingIgnoreCaseAndIsDeletedFalse(String surname, Pageable pageable);
    Slice<OutstandingPersonEntity> findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCaseAndIsDeletedFalse(String name, String surname, Pageable pageable);
}
