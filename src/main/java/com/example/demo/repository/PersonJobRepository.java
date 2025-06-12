package com.example.demo.repository;

import com.example.demo.entity.PersonJobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJobRepository extends JpaRepository<PersonJobEntity, Long> {
}
