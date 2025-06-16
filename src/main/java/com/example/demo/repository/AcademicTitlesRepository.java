package com.example.demo.repository;

import com.example.demo.entity.AcademicTitlesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface AcademicTitlesRepository extends JpaRepository<AcademicTitlesEntity, Long> {
    Optional<AcademicTitlesEntity> findByNameIgnoreCaseAndDisplayTrue(String name);
    Optional<AcademicTitlesEntity> findByIdAndDisplayTrue(Long id);
    List<AcademicTitlesEntity> findByDisplayTrueOrderByNameAsc();
}
