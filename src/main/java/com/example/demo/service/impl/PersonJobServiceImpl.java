package com.example.demo.service.impl;

import com.example.demo.dao.entity.JobTitleEntity;
import com.example.demo.dao.entity.OutstandingPersonEntity;
import com.example.demo.dao.repository.JobTitleRepository;
import com.example.demo.dao.repository.OutstandingPersonRepository;
import com.example.demo.exceptions.NotFoundException;
import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobResponse;
import com.example.model.PersonJobUpdateRequest;
import com.example.demo.dao.entity.PersonJobEntity;
import com.example.demo.mapper.PersonJobMapper;
import com.example.demo.dao.repository.PersonJobRepository;
import com.example.demo.service.PersonJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonJobServiceImpl implements PersonJobService {
    private final PersonJobRepository repository;
    private final PersonJobMapper mapper;
    private final OutstandingPersonRepository outstandingPersonRepository;
    private final JobTitleRepository jobTitleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PersonJobResponse> findAll() {
        List<PersonJobEntity> personJobEntity = repository.findByIsDeletedFalse();
        return personJobEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonJobResponse> findAllDeleted() {
        List<PersonJobEntity> personJobEntity = repository.findByIsDeletedTrue();
        return personJobEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonJobResponse findById(Long id) {
        PersonJobEntity personJobEntity = findPersonJobById(id);
        return mapper.toResponse(personJobEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonJobResponse findDeletedById(Long id) {
        PersonJobEntity personJobEntity = findDeletedPersonJobById(id);
        return mapper.toResponse(personJobEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonJobResponse> findByPersonId(Long personId) {
        List<PersonJobEntity> personJobEntity = repository.findByPersonIdAndIsDeletedFalseOrderByStartYearDesc(personId);
        return personJobEntity.stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PersonJobResponse findByPersonIdAndCurrent(Long personId) {
        PersonJobEntity personJobEntity =  repository.findByPersonIdAndCurrentTrueAndIsDeletedFalse(personId).orElseThrow(
                () -> new NotFoundException("The current job of the person with id " + personId + " was not found." )
        );
        return mapper.toResponse(personJobEntity);
    }

    @Override
    @Transactional
    public PersonJobResponse create(PersonJobCreateRequest personJobCreateRequest) {
        PersonJobEntity personJobEntity = mapper.toEntity(personJobCreateRequest);
        setPersonFromRequest(personJobEntity, personJobCreateRequest.getPersonId());
        setJobTitleFromRequest(personJobEntity, personJobCreateRequest.getJobTitle());
        repository.save(personJobEntity);
        return mapper.toResponse(personJobEntity);
    }

    @Override
    @Transactional
    public PersonJobResponse update(PersonJobUpdateRequest personJobUpdateRequest, Long id) {
        PersonJobEntity personJobEntity = findPersonJobById(id);
        mapper.updateEntity(personJobEntity, personJobUpdateRequest);
        setPersonFromRequest(personJobEntity, personJobUpdateRequest.getPersonId());
        setJobTitleFromRequest(personJobEntity, personJobUpdateRequest.getJobTitle());
        return mapper.toResponse(personJobEntity);
    }

    @Override
    @Transactional
    public PersonJobResponse recover(Long id) {
        PersonJobEntity personJobEntity = findDeletedPersonJobById(id);
        personJobEntity.setIsDeleted(false);
        return mapper.toResponse(personJobEntity);
    }

    private PersonJobEntity findPersonJobById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("The Information not found with id: " + id )
        );
    }

    private PersonJobEntity findDeletedPersonJobById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException("The deleted information not found with id: " + id )
        );
    }

    private void setPersonFromRequest(PersonJobEntity entity, Long personId) {
        OutstandingPersonEntity person = outstandingPersonRepository.findByIdAndIsDeletedFalse(personId).orElseThrow(
                () -> new NotFoundException("Person not found with id: " + personId)
        );
        entity.setPerson(person);
    }

    private void setJobTitleFromRequest(PersonJobEntity entity, String jobTitle) {
        JobTitleEntity jobTitleEntity = jobTitleRepository.findByNameIgnoreCaseAndIsDeletedFalse(jobTitle).orElseThrow(
                () -> new NotFoundException("Job title " + jobTitle + " not found")
        );
        entity.setJobTitle(jobTitleEntity);
    }
}
