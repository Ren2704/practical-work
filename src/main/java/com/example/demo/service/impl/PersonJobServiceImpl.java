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
    private final PersonJobRepository personJobRepository; private final PersonJobMapper personJobMapper;
    public PersonJobServiceImpl(PersonJobRepository personJobRepository, PersonJobMapper personJobMapper) {
        this.personJobRepository = personJobRepository;
        this.personJobMapper = personJobMapper;
    }
    @Override
    public List<PersonJobEntity> findAll() {
        return personJobRepository.findAll();
    }
    @Override
    public Optional<PersonJobEntity> findById(Long id) {
        return personJobRepository.findById(id);
    }
    @Override
    public PersonJobEntity create(PersonJobCreateRequest personJobCreateRequest) {
        PersonJobEntity personJobEntity = new PersonJobEntity();
        personJobEntity = personJobMapper.requestMapToPersonJob(personJobEntity, personJobCreateRequest);
        return personJobRepository.save(personJobEntity);
    }
    @Override
    public PersonJobEntity update(PersonJobUpdateRequest personJobUpdateRequest) {
        Optional<PersonJobEntity> optionalPersonJob = personJobRepository.findById(personJobUpdateRequest.getId());
        if (optionalPersonJob.isPresent()) {
            PersonJobEntity personJobEntity = optionalPersonJob.get();
            personJobEntity = personJobMapper.requestMapToPersonJob(personJobEntity, personJobUpdateRequest);
            return personJobRepository.save(personJobEntity);
        }
        return null;
    }
    @Override
    public void delete(Long id) {
        personJobRepository.deleteById(id);
    }
}
