package com.example.demo.repository;

import com.example.demo.entity.EducationSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationSubjectRepository extends JpaRepository<EducationSubjectEntity, Long> {
    Optional<EducationSubjectEntity> findByName(String name);
}
