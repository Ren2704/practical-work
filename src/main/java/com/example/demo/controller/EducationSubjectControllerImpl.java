package com.example.demo.controller;

import com.example.api.EducationSubjectControllerApi;
import com.example.demo.entity.EducationSubjectEntity;
import com.example.demo.mapper.EducationSubjectMapper;
import com.example.demo.service.EducationSubjectService;
import com.example.model.EducationSubjectCreateRequest;
import com.example.model.EducationSubjectResponse;
import com.example.model.EducationSubjectUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EducationSubjectControllerImpl implements EducationSubjectControllerApi {

    private final EducationSubjectService educationSubjectService;
    private final EducationSubjectMapper educationSubjectMapper;

    @Override
    public ResponseEntity<EducationSubjectResponse> createEducationSubject(EducationSubjectCreateRequest educationSubjectCreateRequest) {
        EducationSubjectEntity educationSubjecEntity = educationSubjectService.create(educationSubjectCreateRequest);
        return new ResponseEntity<>(educationSubjectMapper.entityMapToResponse(educationSubjecEntity), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<EducationSubjectResponse>> findAllEducationSubjects() {
        List<EducationSubjectEntity> educationSubjecEntities = educationSubjectService.findAll();
        List<EducationSubjectResponse> responseList = educationSubjectMapper.entityMapToResponseList(educationSubjecEntities);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EducationSubjectResponse> updateEducationSubject(Long id, EducationSubjectUpdateRequest educationSubjectUpdateRequest) {
        EducationSubjectEntity educationSubjecEntity = educationSubjectService.update(educationSubjectUpdateRequest, id);
        return new ResponseEntity<>(educationSubjectMapper.entityMapToResponse(educationSubjecEntity), HttpStatus.OK);
    }
}
