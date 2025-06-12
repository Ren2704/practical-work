package com.example.demo.repository;

import com.example.demo.entity.JobTitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobTitleRepository extends JpaRepository<JobTitleEntity, Long> {
    Optional<JobTitleEntity> findByName(String name);
}
