package com.example.demo.mapper.impl;


import com.example.demo.controller.request.job.person.PersonJobCreateRequest;
import com.example.demo.entity.JobTitleEntity;
import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.demo.entity.PersonJobEntity;
import com.example.demo.mapper.PersonJobMapper;
import com.example.demo.repository.JobTitleRepository;
import com.example.demo.repository.OutstandingPeopleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PersonJobMapperImpl implements PersonJobMapper {
    private final OutstandingPeopleRepository outstandingPeopleRepository;
    private final JobTitleRepository jobTitleRepository;

    public PersonJobMapperImpl(OutstandingPeopleRepository outstandingPeopleRepository, JobTitleRepository jobTitleRepository) {
        this.outstandingPeopleRepository = outstandingPeopleRepository;
        this.jobTitleRepository = jobTitleRepository;
    }

    @Override
    public <T extends PersonJobCreateRequest> PersonJobEntity requestMapToPersonJob(PersonJobEntity personJobEntity, T request) {
        if (request == null)
            return null;
        personJobEntity.setStartYear(request.getStartYear());
        personJobEntity.setEndYear(request.getEndYear());
        personJobEntity.setCurrent(request.isCurrent());
        Optional<OutstandingPeopleEntity> person = outstandingPeopleRepository.findById(request.getPersonId());
        if (person.isPresent())
            personJobEntity.setPerson(person.get());
        Optional<JobTitleEntity> jobTitle = jobTitleRepository.findByName(request.getJobTitle());
        if (jobTitle.isPresent())
            personJobEntity.setJobTitleEntity(jobTitle.get());
        return personJobEntity;
    }
}
