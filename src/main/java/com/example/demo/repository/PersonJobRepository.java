package com.example.demo.repository;


import com.example.demo.entity.PersonJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonJobRepository extends JpaRepository<PersonJobEntity, Long> {
    List<PersonJobEntity> findByPersonIdAndDisplayTrueOrderByStartYearDesc(Long personId);
    Optional<PersonJobEntity> findByPersonIdAndCurrentTrueAndDisplayTrue(Long personId);
    Optional<PersonJobEntity> findByIdAndDisplayTrue(Long id);
}
