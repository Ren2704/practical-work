package com.example.demo.service.impl;

import com.example.demo.controller.request.education.subject.EducationSubjectCreateRequest;
import com.example.demo.controller.request.education.subject.EducationSubjectUpdateRequest;
import com.example.demo.entity.EducationSubjectEntity;
import com.example.demo.mapper.EducationSubjectMapper;
import com.example.demo.repository.EducationSubjectRepository;
import com.example.demo.service.EducationSubjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationSubjectServiceImpl implements EducationSubjectService {

    private final EducationSubjectRepository educationSubjectRepository;
    private final EducationSubjectMapper educationSubjectMapper;

    public EducationSubjectServiceImpl(EducationSubjectRepository educationSubjectRepository, EducationSubjectMapper educationSubjectMapper) {
        this.educationSubjectRepository = educationSubjectRepository;
        this.educationSubjectMapper = educationSubjectMapper;
    }

    @Override
    public List<EducationSubjectEntity> findAll() {
        return educationSubjectRepository.findAll();
    }

    @Override
    public Optional<EducationSubjectEntity> findById(Long id) {
        return educationSubjectRepository.findById(id);
    }

    @Override
    public EducationSubjectEntity create(EducationSubjectCreateRequest educationSubjectCreateRequest) {
        EducationSubjectEntity educationSubjectEntity = new EducationSubjectEntity();
        educationSubjectEntity = educationSubjectMapper.requestMapToEducationSubject(educationSubjectEntity, educationSubjectCreateRequest);
        return educationSubjectRepository.save(educationSubjectEntity);
    }

    @Override
    public EducationSubjectEntity update(EducationSubjectUpdateRequest educationSubjectUpdateRequest) {
        Optional<EducationSubjectEntity> optionalScientificFields = educationSubjectRepository.findById(educationSubjectUpdateRequest.getId());
        if (optionalScientificFields.isPresent()) {
            EducationSubjectEntity educationSubjectEntity = optionalScientificFields.get();
            educationSubjectEntity = educationSubjectMapper.requestMapToEducationSubject(educationSubjectEntity, educationSubjectUpdateRequest);
            return educationSubjectRepository.save(educationSubjectEntity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        educationSubjectRepository.deleteById(id);
    }
}
