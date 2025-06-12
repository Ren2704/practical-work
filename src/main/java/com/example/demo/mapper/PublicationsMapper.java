package com.example.demo.mapper;

import com.example.demo.controller.request.publications.PublicationsCreateRequest;
import com.example.demo.entity.PublicationsEntity;

public interface PublicationsMapper {
    <T extends PublicationsCreateRequest> PublicationsEntity requestMapToPublications(PublicationsEntity publicationsEntity, T request);
}
