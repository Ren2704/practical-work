package com.example.demo.dao.repository;

import com.example.demo.dao.entity.EducationSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
