package com.example.demo.mapper.impl;

import com.example.demo.controller.request.publications.PublicationsCreateRequest;
import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.demo.entity.PublicationsEntity;
import com.example.demo.mapper.PublicationsMapper;
import com.example.demo.repository.OutstandingPeopleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class PublicationsMapperImpl implements PublicationsMapper {

    private final OutstandingPeopleRepository outstandingPeopleRepository;

    public PublicationsMapperImpl(OutstandingPeopleRepository outstandingPeopleRepository){
        this.outstandingPeopleRepository = outstandingPeopleRepository;
    }
    @Override
    public <T extends PublicationsCreateRequest> PublicationsEntity requestMapToPublications(PublicationsEntity publicationsEntity, T request) {
        if (request == null)
            return null;
        publicationsEntity.setTitle(request.getTitle());
        publicationsEntity.setYear(request.getYear());
        publicationsEntity.setLink(request.getLink());

        Optional<OutstandingPeopleEntity> person = outstandingPeopleRepository.findById(request.getPersonId());
        if (person.isPresent())
            publicationsEntity.setPerson(person.get());

        return publicationsEntity;
    }
}
