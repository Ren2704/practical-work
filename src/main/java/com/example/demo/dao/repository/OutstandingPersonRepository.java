package com.example.demo.dao.repository;

import com.example.demo.dao.entity.OutstandingPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface OutstandingPersonRepository extends JpaRepository<OutstandingPersonEntity, Long> {
    List<OutstandingPersonEntity> findByIsDeletedFalseOrderBySurnameAsc();
    List<OutstandingPersonEntity> findByIsDeletedTrueOrderBySurnameAsc();
    Optional<OutstandingPersonEntity> findByIdAndIsDeletedFalse(Long id);
    Optional<OutstandingPersonEntity> findByIdAndIsDeletedTrue(Long id);
    List<OutstandingPersonEntity> findByNameContainingIgnoreCaseAndIsDeletedFalse(String name);
    List<OutstandingPersonEntity> findBySurnameContainingIgnoreCaseAndIsDeletedFalse(String surname);
    List<OutstandingPersonEntity> findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCaseAndIsDeletedFalse(String name, String surname);
}
