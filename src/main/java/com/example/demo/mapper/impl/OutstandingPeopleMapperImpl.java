package com.example.demo.mapper.impl;


import com.example.demo.controller.request.outstanding.people.OutstandingPeopleCreateRequest;
import com.example.demo.entity.AcademicDegreesEntity;
import com.example.demo.entity.AcademicTitlesEntity;
import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.demo.entity.EducationSubjectEntity;
import com.example.demo.mapper.OutstandingPeopleMapper;
import com.example.demo.repository.AcademicDegreesRepository;
import com.example.demo.repository.AcademicTitlesRepository;
import com.example.demo.repository.EducationSubjectRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class OutstandingPeopleMapperImpl implements OutstandingPeopleMapper {

    private final AcademicTitlesRepository academicTitlesRepository;
    private final AcademicDegreesRepository academicDegreesRepository;
    private final EducationSubjectRepository scientificFieldsRepository;

    public OutstandingPeopleMapperImpl(AcademicTitlesRepository academicTitlesRepository, AcademicDegreesRepository academicDegreesRepository, EducationSubjectRepository scientificFieldsRepository){
        this.academicTitlesRepository = academicTitlesRepository;
        this.academicDegreesRepository = academicDegreesRepository;
        this.scientificFieldsRepository = scientificFieldsRepository;
    }
    @Override
    public <T extends OutstandingPeopleCreateRequest> OutstandingPeopleEntity requestMapToOutstandingPeople(OutstandingPeopleEntity person, T request) {
        if (request == null)
            return null;
        person.setName(request.getName());
        person.setSurname(request.getSurname());
        person.setPatronymic(request.getPatronymic());
        person.setGender(request.getGender());
        person.setYearOfBirth(request.getYearOfBirth());
        person.setYearOfDeath(request.getYearOfDeath());
        person.setPhotoUrl(person.getPhotoUrl());
        person.setBiography(request.getBiography());

        Optional<AcademicTitlesEntity> academicTitles = academicTitlesRepository.findByNameIgnoreCaseAndDisplayTrue(request.getAcademicTitles());
        if (academicTitles.isPresent())
            person.setAcademicTitles(academicTitles.get());

        Optional<AcademicDegreesEntity> academicDegrees = academicDegreesRepository.findByNameIgnoreCaseAndDisplayTrue(request.getAcademicDegrees());
        if (academicDegrees.isPresent())
            person.setAcademicDegrees(academicDegrees.get());

        Optional<EducationSubjectEntity> scientificFields = scientificFieldsRepository.findByNameIgnoreCaseAndDisplayTrue(request.getScientificFields());
        if (scientificFields.isPresent())
            person.setEducationSubject(scientificFields.get());

        return person;
    }
}
