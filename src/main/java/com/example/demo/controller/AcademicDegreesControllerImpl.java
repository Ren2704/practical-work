package com.example.demo.controller;

import com.example.api.AcademicDegreesControllerApi;
import com.example.demo.entity.AcademicDegreesEntity;
import com.example.demo.mapper.AcademicDegreesMapper;
import com.example.demo.service.AcademicDegreesService;
import com.example.model.AcademicDegreesCreateRequest;
import com.example.model.AcademicDegreesResponse;
import com.example.model.AcademicDegreesUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AcademicDegreesControllerImpl implements AcademicDegreesControllerApi {

    private final AcademicDegreesService academicDegreesService;
    private final AcademicDegreesMapper academicDegreesMapper;

    @Override
    public ResponseEntity<AcademicDegreesResponse> createAcademicDegree(AcademicDegreesCreateRequest academicDegreesCreateRequest) {
        AcademicDegreesEntity academicDegreesEntity = academicDegreesService.create(academicDegreesCreateRequest);
        return new ResponseEntity<>(academicDegreesMapper.entityMapToResponse(academicDegreesEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<AcademicDegreesResponse>> findAllAcademicDegrees() {
        List<AcademicDegreesEntity> academicDegreesEntities = academicDegreesService.findAll();
        List<AcademicDegreesResponse> responseList = academicDegreesMapper.entityMapToResponseList(academicDegreesEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AcademicDegreesResponse> updateAcademicDegree(Long id, AcademicDegreesUpdateRequest academicDegreesUpdateRequest) {
        AcademicDegreesEntity academicDegreesEntity = academicDegreesService.update(academicDegreesUpdateRequest, id);
        return new ResponseEntity<>(academicDegreesMapper.entityMapToResponse(academicDegreesEntity), HttpStatus.OK);
    }
}
