package com.example.demo.controller;

import com.example.api.AcademicTitlesControllerApi;
import com.example.demo.entity.AcademicTitlesEntity;
import com.example.demo.mapper.AcademicTitlesMapper;
import com.example.demo.service.AcademicTitlesService;
import com.example.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AcademicTitlesControllerImpl implements AcademicTitlesControllerApi {

    private final AcademicTitlesService academicTitlesService;
    private final AcademicTitlesMapper academicTitlesMapper;

    @Override
    public ResponseEntity<AcademicTitlesResponse> createAcademicTitle(AcademicTitlesCreateRequest academicTitlesCreateRequest) {
        AcademicTitlesEntity academicTitlesEntity = academicTitlesService.create(academicTitlesCreateRequest);
        return new ResponseEntity<>(academicTitlesMapper.entityMapToResponse(academicTitlesEntity), HttpStatus.CREATED);    }

    @Override
    public ResponseEntity<List<AcademicTitlesResponse>> findAllAcademicTitles() {
        List<AcademicTitlesEntity> academicTitlesEntity = academicTitlesService.findAll();
        List<AcademicTitlesResponse> responseList = academicTitlesMapper.entityMapToResponseList(academicTitlesEntity);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AcademicTitlesResponse> updateAcademicTitle(Long id, AcademicTitlesUpdateRequest academicTitlesUpdateRequest) {
        AcademicTitlesEntity academicTitlesEntity = academicTitlesService.update(academicTitlesUpdateRequest, id);
        return new ResponseEntity<>(academicTitlesMapper.entityMapToResponse(academicTitlesEntity), HttpStatus.OK);
    }
}