package com.example.demo.dao.repository;

import com.example.demo.dao.entity.AcademicTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
