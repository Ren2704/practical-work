package com.example.demo.service.impl;

import com.example.demo.controller.request.publications.PublicationsCreateRequest;
import com.example.demo.controller.request.publications.PublicationsUpdateRequest;
import com.example.demo.entity.PublicationsEntity;
import com.example.demo.mapper.PublicationsMapper;
import com.example.demo.repository.PublicationsRepository;
import com.example.demo.service.PublicationsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationsServiceImpl implements PublicationsService {

    private final PublicationsRepository publicationsRepository;
    private final PublicationsMapper publicationsMapper;

    public PublicationsServiceImpl(PublicationsRepository publicationsRepository, PublicationsMapper publicationsMapper) {
        this.publicationsRepository = publicationsRepository;
        this.publicationsMapper = publicationsMapper;
    }

    @Override
    public List<PublicationsEntity> findAll() {
        return publicationsRepository.findAll();
    }

    @Override
    public Optional<PublicationsEntity> findById(Long id) {
        return publicationsRepository.findById(id);
    }

    @Override
    public PublicationsEntity create(PublicationsCreateRequest publicationsCreateRequest) {
        PublicationsEntity publicationsEntity = new PublicationsEntity();
        publicationsEntity = publicationsMapper.requestMapToPublications(publicationsEntity, publicationsCreateRequest);
        return publicationsRepository.save(publicationsEntity);
    }

    @Override
    public PublicationsEntity update(PublicationsUpdateRequest publicationsUpdateRequest) {
        Optional<PublicationsEntity> optionalPublications = publicationsRepository.findById(publicationsUpdateRequest.getId());
        if (optionalPublications.isPresent()) {
            PublicationsEntity publicationsEntity = optionalPublications.get();
            publicationsEntity = publicationsMapper.requestMapToPublications(publicationsEntity, publicationsUpdateRequest);
            return publicationsRepository.save(publicationsEntity);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        publicationsRepository.findById(id);
    }
}
