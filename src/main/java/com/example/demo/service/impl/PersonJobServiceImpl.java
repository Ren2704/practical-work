package com.example.demo.service.impl;

import com.example.demo.controller.request.job.person.PersonJobCreateRequest;
import com.example.demo.controller.request.job.person.PersonJobUpdateRequest;
import com.example.demo.entity.PersonJobEntity;
import com.example.demo.mapper.PersonJobMapper;
import com.example.demo.repository.PersonJobRepository;
import com.example.demo.service.PersonJobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonJobServiceImpl implements PersonJobService {
    private final PersonJobRepository personJobRepository;
    private final PersonJobMapper personJobMapper;

    public PersonJobServiceImpl(PersonJobRepository personJobRepository, PersonJobMapper personJobMapper) {
        this.personJobRepository = personJobRepository;
        this.personJobMapper = personJobMapper;
    }

    @Override
    public List<PersonJobEntity> findByPersonId(Long personId) {
        return personJobRepository.findByPersonIdAndDisplayTrueOrderByStartYearDesc(personId);
    }

    @Override
    public Optional<PersonJobEntity> findByPersonIdAndCurrent(Long personId) {
        return personJobRepository.findByPersonIdAndCurrentTrueAndDisplayTrue(personId);
    }

    @Override
    public PersonJobEntity create(PersonJobCreateRequest personJobCreateRequest) {
        PersonJobEntity personJob = new PersonJobEntity();
        personJob = personJobMapper.requestMapToPersonJob(personJob, personJobCreateRequest);
        return personJobRepository.save(personJob);
    }

    @Override
    public PersonJobEntity update(PersonJobUpdateRequest personJobUpdateRequest) {
        Optional<PersonJobEntity> optionalPersonJob = personJobRepository.findByIdAndDisplayTrue(personJobUpdateRequest.getId());
        if (optionalPersonJob.isPresent()) {
            PersonJobEntity personJob = optionalPersonJob.get();
            personJob = personJobMapper.requestMapToPersonJob(personJob, personJobUpdateRequest);
            return personJobRepository.save(personJob);
        }
        return null;
    }
}
