package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.AcademicDegreesRepository;
import com.example.demo.repository.AcademicTitlesRepository;
import com.example.demo.repository.EducationSubjectRepository;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonUpdateRequest;
import com.example.demo.mapper.OutstandingPeopleMapper;
import com.example.demo.repository.OutstandingPeopleRepository;
import com.example.demo.service.OutstandingPeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OutstandingPeopleServiceImpl implements OutstandingPeopleService {
    private final OutstandingPeopleRepository outstandingPeopleRepository;
    private final OutstandingPeopleMapper outstandingPeopleMapper;
    private final EducationSubjectRepository educationSubjectRepository;
    private final AcademicDegreesRepository academicDegreeRepository;
    private final AcademicTitlesRepository academicTitleRepository;


    @Override
    @Transactional(readOnly = true)
    public List<OutstandingPeopleEntity> findAll() {
        return outstandingPeopleRepository.findByDisplayTrueOrderBySurnameAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OutstandingPeopleEntity> findById(Long id) {
        return outstandingPeopleRepository.findByIdAndDisplayTrue(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutstandingPeopleEntity> findByName(String name) {
        return outstandingPeopleRepository.findByNameContainingIgnoreCaseAndDisplayTrue(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutstandingPeopleEntity> findBySurname(String surname) {
        return outstandingPeopleRepository.findBySurnameContainingIgnoreCaseAndDisplayTrue(surname);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutstandingPeopleEntity> findByNameAndSurname(String name, String surname) {
        return outstandingPeopleRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCaseAndDisplayTrue(name, surname);
    }

    @Override
    @Transactional
    public OutstandingPeopleEntity create(OutstandingPersonCreateRequest outstandingPeopleCreateRequest) {
        OutstandingPeopleEntity outstandingPeopleEntity =  outstandingPeopleMapper.requestMapToEntity(outstandingPeopleCreateRequest);
        setAcademicDegreeFromRequest(outstandingPeopleEntity, outstandingPeopleCreateRequest.getAcademicDegree());
        AcademicTitleFromRequest(outstandingPeopleEntity, outstandingPeopleCreateRequest.getAcademicTitle());
        setEducationSubjectFromRequest(outstandingPeopleEntity, outstandingPeopleCreateRequest.getEducationSubject());
        return outstandingPeopleRepository.save(outstandingPeopleEntity);
    }
    @Override
    @Transactional
    public OutstandingPeopleEntity update(OutstandingPersonUpdateRequest outstandingPeopleUpdateRequest) {
        Optional<OutstandingPeopleEntity> optionalOutstandingPeople = outstandingPeopleRepository.findByIdAndDisplayTrue(outstandingPeopleUpdateRequest.getId());
        if (optionalOutstandingPeople.isPresent()) {
            OutstandingPeopleEntity outstandingPeopleEntity = optionalOutstandingPeople.get();
            outstandingPeopleMapper.updateEntity(outstandingPeopleEntity, outstandingPeopleUpdateRequest);
            if (outstandingPeopleUpdateRequest.getAcademicDegree() != null) {
                setAcademicDegreeFromRequest(outstandingPeopleEntity, outstandingPeopleUpdateRequest.getAcademicDegree());
            }
            if (outstandingPeopleUpdateRequest.getAcademicTitle() != null) {
                AcademicTitleFromRequest(outstandingPeopleEntity, outstandingPeopleUpdateRequest.getAcademicTitle());
            }
            if (outstandingPeopleUpdateRequest.getEducationSubject() != null) {
                setEducationSubjectFromRequest(outstandingPeopleEntity, outstandingPeopleUpdateRequest.getEducationSubject());
            }
            return outstandingPeopleRepository.save(outstandingPeopleEntity);
        }
        return null;
    }

    private void setAcademicDegreeFromRequest(OutstandingPeopleEntity entity, String academicDegreeName) {
        Optional<AcademicDegreesEntity> academicDegree = academicDegreeRepository.findByNameIgnoreCaseAndDisplayTrue(academicDegreeName);
        if (academicDegree.isPresent())
            entity.setAcademicDegrees(academicDegree.get());
    }

    private void AcademicTitleFromRequest(OutstandingPeopleEntity entity, String academicTitleName) {
        Optional<AcademicTitlesEntity> academicTitle = academicTitleRepository.findByNameIgnoreCaseAndDisplayTrue(academicTitleName);
        if (academicTitle.isPresent())
            entity.setAcademicTitles(academicTitle.get());
    }

    private void setEducationSubjectFromRequest(OutstandingPeopleEntity entity, String educationSubjectName) {
        Optional<EducationSubjectEntity> educationSubject = educationSubjectRepository.findByNameIgnoreCaseAndDisplayTrue(educationSubjectName);
        if (educationSubject.isPresent())
            entity.setEducationSubject(educationSubject.get());
    }
}
