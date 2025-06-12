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
        return outstandingPeopleRepository.findAll();
    }
    @Override
    public Optional<OutstandingPeopleEntity> findById(Long id) {
        return outstandingPeopleRepository.findById(id);
    }
    @Override
    public OutstandingPeopleEntity create(OutstandingPeopleCreateRequest outstandingPeopleCreateRequest) {
        OutstandingPeopleEntity outstandingPeopleEntity = new OutstandingPeopleEntity();
        outstandingPeopleEntity =
                outstandingPeopleMapper.requestMapToOutstandingPeople(outstandingPeopleEntity, outstandingPeopleCreateRequest);
        return outstandingPeopleRepository.save(outstandingPeopleEntity);
    }
    @Override
    public OutstandingPeopleEntity update(OutstandingPeopleUpdateRequest outstandingPeopleUpdateRequest) {
        Optional<OutstandingPeopleEntity> optionalOutstandingPeople =
                outstandingPeopleRepository.findById(outstandingPeopleUpdateRequest.getId());
        if (optionalOutstandingPeople.isPresent()) {
            OutstandingPeopleEntity outstandingPeopleEntity = optionalOutstandingPeople.get();
            outstandingPeopleEntity =
                    outstandingPeopleMapper.requestMapToOutstandingPeople(outstandingPeopleEntity, outstandingPeopleUpdateRequest);
            return outstandingPeopleRepository.save(outstandingPeopleEntity);
        }
        return null;
    }
    @Override public void delete(Long id) {
        outstandingPeopleRepository.deleteById(id);
    }
}
