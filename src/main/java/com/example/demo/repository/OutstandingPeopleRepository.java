package com.example.demo.repository;

import com.example.demo.entity.OutstandingPeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutstandingPeopleRepository extends JpaRepository<OutstandingPeopleEntity, Long> {
}
