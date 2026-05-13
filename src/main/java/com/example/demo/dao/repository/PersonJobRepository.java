package com.example.demo.dao.repository;


import com.example.demo.dao.entity.PersonJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonJobRepository extends JpaRepository<PersonJobEntity, Long> {
    List<PersonJobEntity> findByIsDeletedFalse();
    List<PersonJobEntity> findByIsDeletedTrue();
    Optional<PersonJobEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<PersonJobEntity> findByIdAndIsDeletedTrue(Long id);
    List<PersonJobEntity> findByPersonIdAndIsDeletedFalseOrderByStartYearDesc(Long personId);
    Optional<PersonJobEntity> findByPersonIdAndCurrentTrueAndIsDeletedFalse(Long personId);
}
