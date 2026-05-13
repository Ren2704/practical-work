package com.example.demo.dao.repository;

import com.example.demo.dao.entity.JobTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
