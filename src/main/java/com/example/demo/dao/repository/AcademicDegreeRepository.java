package com.example.demo.dao.repository;

import com.example.demo.dao.entity.AcademicDegreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
