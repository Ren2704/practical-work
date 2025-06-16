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
        return educationSubjectRepository.findByDisplayTrueOrderByNameAsc();
    }
    @Override
    public EducationSubjectEntity create(EducationSubjectCreateRequest educationSubjectCreateRequest) {
        EducationSubjectEntity educationSubject = new EducationSubjectEntity();
        educationSubject = educationSubjectMapper.requestMapToEducationSubject(educationSubject, educationSubjectCreateRequest);
        return educationSubjectRepository.save(educationSubject);
    }

    @Override
    public EducationSubjectEntity update(EducationSubjectUpdateRequest educationSubjectUpdateRequest) {
        Optional<EducationSubjectEntity> optionalScientificFields = educationSubjectRepository.findByIdAndDisplayTrue(educationSubjectUpdateRequest.getId());
        if (optionalScientificFields.isPresent()) {
            EducationSubjectEntity educationSubject = optionalScientificFields.get();
            educationSubject = educationSubjectMapper.requestMapToEducationSubject(educationSubject, educationSubjectUpdateRequest);
            return educationSubjectRepository.save(educationSubject);
        }
        return null;
    }
}