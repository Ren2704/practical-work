package com.example.demo.service.impl;

import com.example.demo.controller.request.outstanding.people.OutstandingPeopleCreateRequest;
import com.example.demo.controller.request.outstanding.people.OutstandingPeopleUpdateRequest;
import com.example.demo.entity.OutstandingPeopleEntity;
import com.example.demo.mapper.OutstandingPeopleMapper;
import com.example.demo.repository.OutstandingPeopleRepository;
import com.example.demo.service.OutstandingPeopleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutstandingPeopleServiceImpl implements OutstandingPeopleService {
    private final OutstandingPeopleRepository outstandingPeopleRepository;
    private final OutstandingPeopleMapper outstandingPeopleMapper;

    public OutstandingPeopleServiceImpl(OutstandingPeopleRepository outstandingPeopleRepository, OutstandingPeopleMapper outstandingPeopleMapper) {
        this.outstandingPeopleRepository = outstandingPeopleRepository;
        this.outstandingPeopleMapper = outstandingPeopleMapper;
    }

    @Override
    public List<OutstandingPeopleEntity> findAll() {
        return outstandingPeopleRepository.findByDisplayTrueOrderBySurnameAsc();
    }

    @Override
    public Optional<OutstandingPeopleEntity> findById(Long id) {
        return outstandingPeopleRepository.findByIdAndDisplayTrue(id);
    }

    @Override
    public List<OutstandingPeopleEntity> findByName(String name) {
        return outstandingPeopleRepository.findByNameContainingIgnoreCaseAndDisplayTrue(name);
    }

    @Override
    public List<OutstandingPeopleEntity> findBySurname(String surname) {
        return outstandingPeopleRepository.findBySurnameContainingIgnoreCaseAndDisplayTrue(surname);
    }

    @Override
    public List<OutstandingPeopleEntity> findByNameAndSurname(String name, String surname) {
        return outstandingPeopleRepository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCaseAndDisplayTrue(name, surname);
    }

    @Override
    public OutstandingPeopleEntity create(OutstandingPeopleCreateRequest outstandingPeopleCreateRequest) {
        OutstandingPeopleEntity outstandingPeople = new OutstandingPeopleEntity();
        outstandingPeople = outstandingPeopleMapper.requestMapToOutstandingPeople(outstandingPeople, outstandingPeopleCreateRequest);
        return outstandingPeopleRepository.save(outstandingPeople);
    }
    @Override
    public OutstandingPeopleEntity update(OutstandingPeopleUpdateRequest outstandingPeopleUpdateRequest) {
        Optional<OutstandingPeopleEntity> optionalOutstandingPeople = outstandingPeopleRepository.findByIdAndDisplayTrue(outstandingPeopleUpdateRequest.getId());
        if (optionalOutstandingPeople.isPresent()) {
            OutstandingPeopleEntity outstandingPeople = optionalOutstandingPeople.get();
            outstandingPeople = outstandingPeopleMapper.requestMapToOutstandingPeople(outstandingPeople, outstandingPeopleUpdateRequest);
            return outstandingPeopleRepository.save(outstandingPeople);
        }
        return null;
    }
}
