package com.example.demo.repository;

import com.example.demo.entity.EducationSubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EducationSubjectRepository extends JpaRepository<EducationSubjectEntity, Long> {
    Optional<EducationSubjectEntity> findByNameIgnoreCaseAndDisplayTrue(String name);
    Optional<EducationSubjectEntity> findByIdAndDisplayTrue(Long id);
    List<EducationSubjectEntity> findByDisplayTrueOrderByNameAsc();
}
