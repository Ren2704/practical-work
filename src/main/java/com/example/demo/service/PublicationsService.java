package com.example.demo.service;

import com.example.demo.controller.request.publications.PublicationsCreateRequest;
import com.example.demo.controller.request.publications.PublicationsUpdateRequest;
import com.example.demo.entity.PublicationsEntity;

import java.util.List;
import java.util.Optional;

public interface PublicationsService {
    List<PublicationsEntity> findAll();
    Optional<PublicationsEntity> findById(Long id);
    PublicationsEntity create(PublicationsCreateRequest publicationsCreateRequest);
    PublicationsEntity update(PublicationsUpdateRequest publicationsUpdateRequest);
    void delete(Long id);
}
