package com.example.demo.service.impl;

import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectUpdateRequest;
import com.example.demo.entity.EducationSubjectEntity;
import com.example.demo.mapper.EducationSubjectMapper;
import com.example.demo.repository.EducationSubjectRepository;
import com.example.demo.service.EducationSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EducationSubjectServiceImpl implements EducationSubjectService {

    private final EducationSubjectRepository educationSubjectRepository;
    private final EducationSubjectMapper educationSubjectMapper;

    @Override
    @Transactional(readOnly = true)
    public List<EducationSubjectEntity> findAll() {
        return educationSubjectRepository.findByDisplayTrueOrderByNameAsc();
    }
    @Override
    @Transactional
    public EducationSubjectEntity create(EducationSubjectCreateRequest educationSubjectCreateRequest) {
        EducationSubjectEntity educationSubjectEntity = educationSubjectMapper.requestMapToEntity(educationSubjectCreateRequest);
        return educationSubjectRepository.save(educationSubjectEntity);
    }

    @Override
    @Transactional
    public EducationSubjectEntity update(EducationSubjectUpdateRequest educationSubjectUpdateRequest) {
        Optional<EducationSubjectEntity> optionalEducationSubject = educationSubjectRepository.findByIdAndDisplayTrue(educationSubjectUpdateRequest.getId());
        if (optionalEducationSubject.isPresent()) {
            EducationSubjectEntity educationSubjectEntity = optionalEducationSubject.get();
            educationSubjectMapper.updateEntity(educationSubjectEntity, educationSubjectUpdateRequest);
            return educationSubjectRepository.save(educationSubjectEntity);
        }
        return null;
    }
}