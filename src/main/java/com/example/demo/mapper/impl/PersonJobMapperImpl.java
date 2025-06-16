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
    public <T extends PersonJobCreateRequest> PersonJobEntity requestMapToPersonJob(PersonJobEntity personJob, T request) {
        if (request == null)
            return null;
        personJob.setStartYear(request.getStartYear());
        personJob.setEndYear(request.getEndYear());
        personJob.setCurrent(request.isCurrent());
        Optional<OutstandingPeopleEntity> person = outstandingPeopleRepository.findById(request.getPersonId());
        if (person.isPresent())
            personJob.setPerson(person.get());
        Optional<JobTitleEntity> jobTitle = jobTitleRepository.findByNameIgnoreCaseAndDisplayTrue(request.getJobTitle());
        if (jobTitle.isPresent())
            personJob.setJobTitle(jobTitle.get());
        return personJob;
    }
}
