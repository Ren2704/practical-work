package com.example.demo.service;

import com.example.model.*;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OutstandingPersonService {
    OutstandingPersonResponse findById(Long id);
    OutstandingPersonResponse findDeletedById(Long id);
    PersonSliceResponse findAll(Pageable pageable);
    PersonSliceResponse findAllDeleted(Pageable pageable);
    PersonSliceResponse findByName(String name, Pageable pageable);
    PersonSliceResponse findBySurname(String surname, Pageable pageable);
    PersonSliceResponse findByNameAndSurname(String name, String surname, Pageable pageable);
    OutstandingPersonResponse create(OutstandingPersonCreateRequest outstandingPersonCreateRequest);
    OutstandingPersonResponse update(OutstandingPersonUpdateRequest outstandingPersonUpdateRequest, Long id);
    OutstandingPersonResponse recover (Long id);
    Resource getPhoto(Long id);
    PhotoResponse updatePhoto(Long id, MultipartFile photo);
    List<PersonResponse> recognizePhoto(MultipartFile photo);
}
