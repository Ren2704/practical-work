package com.example.demo.service;

import com.example.model.AcademicTitleCreateRequest;
import com.example.model.AcademicTitleResponse;
import com.example.model.AcademicTitleUpdateRequest;

import java.util.List;

public interface AcademicTitleService {
    List<AcademicTitleResponse> findAll();
    List<AcademicTitleResponse> findAllDeleted();
    AcademicTitleResponse findById(Long id);
    AcademicTitleResponse findDeletedById(Long id);
    AcademicTitleResponse create(AcademicTitleCreateRequest academicTitleCreateRequest);
    AcademicTitleResponse update(AcademicTitleUpdateRequest academicTitleUpdateRequest, Long id);
    AcademicTitleResponse recover (Long id);
}
