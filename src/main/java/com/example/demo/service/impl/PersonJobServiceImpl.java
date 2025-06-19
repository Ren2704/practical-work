package com.example.demo.service.impl;

import com.example.demo.entity.JobTitleEntity;
import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.demo.repository.JobTitleRepository;
import com.example.demo.repository.OutstandingPeopleRepository;
import com.example.model.PersonJobCreateRequest;
import com.example.model.PersonJobUpdateRequest;
import com.example.demo.entity.PersonJobEntity;
import com.example.demo.mapper.PersonJobMapper;
import com.example.demo.repository.PersonJobRepository;
import com.example.demo.service.PersonJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonJobServiceImpl implements PersonJobService {
    private final PersonJobRepository personJobRepository;
    private final PersonJobMapper personJobMapper;
    private final OutstandingPeopleRepository outstandingPeopleRepository;
    private final JobTitleRepository jobTitleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PersonJobEntity> findByPersonId(Long personId) {
        return personJobRepository.findByPersonIdAndDisplayTrueOrderByStartYearDesc(personId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonJobEntity> findByPersonIdAndCurrent(Long personId) {
        return personJobRepository.findByPersonIdAndCurrentTrueAndDisplayTrue(personId);
    }

    @Override
    @Transactional
    public PersonJobEntity create(PersonJobCreateRequest personJobCreateRequest) {
        PersonJobEntity personJobEntity = personJobMapper.requestMapToEntity(personJobCreateRequest);
        setPersonFromRequest(personJobEntity, personJobCreateRequest.getPersonId());
        setJobTitleFromRequest(personJobEntity, personJobCreateRequest.getJobTitle());
        return personJobRepository.save(personJobEntity);
    }

    @Override
    @Transactional
    public PersonJobEntity update(PersonJobUpdateRequest personJobUpdateRequest) {
        Optional<PersonJobEntity> optionalPersonJob = personJobRepository.findByIdAndDisplayTrue(personJobUpdateRequest.getId());
        if (optionalPersonJob.isPresent()) {
            PersonJobEntity personJobEntity = optionalPersonJob.get();
            personJobMapper.updateEntity(personJobEntity, personJobUpdateRequest);
            if (personJobUpdateRequest.getPersonId() != null) {
                setPersonFromRequest(personJobEntity, personJobUpdateRequest.getPersonId());
            }
            if (personJobUpdateRequest.getJobTitle() != null) {
                setJobTitleFromRequest(personJobEntity, personJobUpdateRequest.getJobTitle());
            }
            return personJobRepository.save(personJobEntity);
        }
        return null;
    }

    private void setPersonFromRequest(PersonJobEntity entity, Long personId) {
        Optional<OutstandingPeopleEntity> person = outstandingPeopleRepository.findByIdAndDisplayTrue(personId);
        if (person.isPresent())
            entity.setPerson(person.get());
    }

    private void setJobTitleFromRequest(PersonJobEntity entity, String nameJobTitle) {
        Optional<JobTitleEntity> jobTitle = jobTitleRepository.findByNameIgnoreCaseAndDisplayTrue(nameJobTitle);
        if (jobTitle.isPresent())
            entity.setJobTitle(jobTitle.get());
    }
}
