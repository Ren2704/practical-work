package com.example.demo.repository;

import com.example.demo.entity.AcademicDegreesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AcademicDegreesRepository extends JpaRepository<AcademicDegreesEntity, Long> {
    Optional<AcademicDegreesEntity> findByNameIgnoreCaseAndDisplayTrue(String name);
    Optional<AcademicDegreesEntity> findByIdAndDisplayTrue(Long id);
    List<AcademicDegreesEntity> findByDisplayTrueOrderByNameAsc();
}
