package com.example.demo.repository;

import com.example.demo.entity.OutstandingPeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface OutstandingPeopleRepository extends JpaRepository<OutstandingPeopleEntity, Long> {
    List<OutstandingPeopleEntity> findByNameContainingIgnoreCaseAndDisplayTrue(String name);
    List<OutstandingPeopleEntity> findBySurnameContainingIgnoreCaseAndDisplayTrue(String surname);
    List<OutstandingPeopleEntity> findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCaseAndDisplayTrue(String name, String surname);
    Optional<OutstandingPeopleEntity> findByIdAndDisplayTrue(Long id);
    List<OutstandingPeopleEntity> findByDisplayTrueOrderBySurnameAsc();
}
