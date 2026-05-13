package com.example.demo.service.impl;

import com.example.demo.dao.entity.*;
import com.example.demo.dao.repository.AcademicDegreeRepository;
import com.example.demo.dao.repository.AcademicTitleRepository;
import com.example.demo.dao.repository.EducationSubjectRepository;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.OutstandingPersonResponse;
import com.example.model.OutstandingPersonCreateRequest;
import com.example.model.OutstandingPersonUpdateRequest;
import com.example.demo.mapper.OutstandingPersonMapper;
import com.example.demo.dao.repository.OutstandingPersonRepository;
import com.example.demo.service.OutstandingPersonService;
import com.example.model.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OutstandingPersonServiceImpl implements OutstandingPersonService {
    private final OutstandingPersonRepository repository;
    private final OutstandingPersonMapper mapper;
    private final EducationSubjectRepository educationSubjectRepository;
    private final AcademicDegreeRepository academicDegreeRepository;
    private final AcademicTitleRepository academicTitleRepository;


    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> findAll() {
        List<OutstandingPersonEntity> outstandingPersonEntity = repository.findByIsDeletedFalseOrderBySurnameAsc();
        return outstandingPersonEntity.stream().map(mapper::toSimpleResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> findAllDeleted() {
        List<OutstandingPersonEntity> outstandingPersonEntity = repository.findByIsDeletedTrueOrderBySurnameAsc();
        return outstandingPersonEntity.stream().map(mapper::toSimpleResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OutstandingPersonResponse findById(Long id) {
        OutstandingPersonEntity outstandingPersonEntity =  findOutstandingPersonById(id);
        return mapper.toResponse(outstandingPersonEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public OutstandingPersonResponse findDeletedById(Long id) {
        OutstandingPersonEntity outstandingPersonEntity = findDeletedOutstandingPersonById(id);
        return mapper.toResponse(outstandingPersonEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> findByName(String name) {
        List<OutstandingPersonEntity> outstandingPersonEntity = repository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name);
        return outstandingPersonEntity.stream().map(mapper::toSimpleResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> findBySurname(String surname) {
        List<OutstandingPersonEntity> outstandingPersonEntity = repository.findBySurnameContainingIgnoreCaseAndIsDeletedFalse(surname);
        return outstandingPersonEntity.stream().map(mapper::toSimpleResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonResponse> findByNameAndSurname(String name, String surname) {
        List<OutstandingPersonEntity> outstandingPersonEntity = repository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCaseAndIsDeletedFalse(name, surname);
        return outstandingPersonEntity.stream().map(mapper::toSimpleResponse).toList();
    }

    @Override
    @Transactional
    public OutstandingPersonResponse create(OutstandingPersonCreateRequest outstandingPersonCreateRequest) {
        OutstandingPersonEntity outstandingPersonEntity =  mapper.toEntity(outstandingPersonCreateRequest);
        setAcademicDegreeFromRequest(outstandingPersonEntity, outstandingPersonCreateRequest.getAcademicDegree());
        setAcademicTitleFromRequest(outstandingPersonEntity, outstandingPersonCreateRequest.getAcademicTitle());
        setEducationSubjectFromRequest(outstandingPersonEntity, outstandingPersonCreateRequest.getEducationSubject());
        repository.save(outstandingPersonEntity);
        return mapper.toResponse(outstandingPersonEntity);
    }
    @Override
    @Transactional
    public OutstandingPersonResponse update(OutstandingPersonUpdateRequest outstandingPersonUpdateRequest, Long id) {
        OutstandingPersonEntity outstandingPersoneEntity = findOutstandingPersonById(id);
        mapper.updateEntity(outstandingPersoneEntity, outstandingPersonUpdateRequest);
        setAcademicDegreeFromRequest(outstandingPersoneEntity, outstandingPersonUpdateRequest.getAcademicDegree());
        setAcademicTitleFromRequest(outstandingPersoneEntity, outstandingPersonUpdateRequest.getAcademicTitle());
        setEducationSubjectFromRequest(outstandingPersoneEntity, outstandingPersonUpdateRequest.getEducationSubject());
        return mapper.toResponse(outstandingPersoneEntity);
    }

    @Override
    @Transactional
    public OutstandingPersonResponse recover(Long id) {
        OutstandingPersonEntity outstandingPersoneEntity = findDeletedOutstandingPersonById(id);
        outstandingPersoneEntity.setIsDeleted(false);
        return mapper.toResponse(outstandingPersoneEntity);
    }

    private OutstandingPersonEntity findOutstandingPersonById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("Outstanding person not found with id: " + id)
        );
    }

    private OutstandingPersonEntity findDeletedOutstandingPersonById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException(" Deleted outstanding person not found with id: " + id)
        );
    }

    private void setAcademicDegreeFromRequest(OutstandingPersonEntity entity, String academicDegreeName) {
        AcademicDegreeEntity academicDegree = academicDegreeRepository.findByNameIgnoreCaseAndIsDeletedFalse(academicDegreeName).orElseThrow(
                () -> new NotFoundException("Academic degree not found with name: " + academicDegreeName)
        );
        entity.setAcademicDegrees(academicDegree);
    }

    private void setAcademicTitleFromRequest(OutstandingPersonEntity entity, String academicTitleName) {
        AcademicTitleEntity academicTitle = academicTitleRepository.findByNameIgnoreCaseAndIsDeletedFalse(academicTitleName).orElseThrow(
                () -> new NotFoundException("Academic title not found with name: " + academicTitleName)
        );
        entity.setAcademicTitles(academicTitle);
    }

    private void setEducationSubjectFromRequest(OutstandingPersonEntity entity, String educationSubjectName) {
        EducationSubjectEntity educationSubject = educationSubjectRepository.findByNameIgnoreCaseAndIsDeletedFalse(educationSubjectName).orElseThrow(
                () -> new NotFoundException("Education subject not found with name: " + educationSubjectName)
        );
        entity.setEducationSubject(educationSubject);
    }
}
