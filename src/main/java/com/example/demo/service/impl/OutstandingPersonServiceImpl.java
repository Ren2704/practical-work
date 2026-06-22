package com.example.demo.service.impl;

import com.example.demo.dao.entity.*;
import com.example.demo.dao.repository.AcademicDegreeRepository;
import com.example.demo.dao.repository.AcademicTitleRepository;
import com.example.demo.dao.repository.EducationSubjectRepository;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.PhotoProcessingException;
import com.example.demo.exceptions.RecognitionException;
import com.example.demo.model.RecognitionResponse;
import com.example.demo.service.CompreFaceService;
import com.example.demo.util.ImageOptimizer;
import com.example.model.*;
import com.example.demo.mapper.OutstandingPersonMapper;
import com.example.demo.dao.repository.OutstandingPersonRepository;
import com.example.demo.service.OutstandingPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OutstandingPersonServiceImpl implements OutstandingPersonService {
    private final OutstandingPersonRepository repository;
    private final OutstandingPersonMapper mapper;
    private final EducationSubjectRepository educationSubjectRepository;
    private final AcademicDegreeRepository academicDegreeRepository;
    private final AcademicTitleRepository academicTitleRepository;
    private final CompreFaceService compreFaceService;

    @Value("${app.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional(readOnly = true)
    public PersonSliceResponse findAll(Pageable pageable) {
        Slice<OutstandingPersonEntity> slice = repository.findByIsDeletedFalseOrderBySurnameAsc(pageable);
        return mapper.toSliceResponse(slice);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonSliceResponse findAllDeleted(Pageable pageable) {
        Slice<OutstandingPersonEntity> slice = repository.findByIsDeletedTrueOrderBySurnameAsc(pageable);
        return mapper.toSliceResponse(slice);
    }

    @Override
    @Transactional(readOnly = true)
    public OutstandingPersonResponse findById(Long id) {
        OutstandingPersonEntity outstandingPersonEntity =  findOutstandingPersonById(id);
        return mapper.toResponse(outstandingPersonEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public OutstandingPersonResponse findDeletedById(Long id) {
        OutstandingPersonEntity outstandingPersonEntity = findDeletedOutstandingPersonById(id);
        return mapper.toResponse(outstandingPersonEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonSliceResponse findByName(String name, Pageable pageable) {
        Slice<OutstandingPersonEntity> slice = repository.findByNameContainingIgnoreCaseAndIsDeletedFalse(name, pageable);
        return mapper.toSliceResponse(slice);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonSliceResponse findBySurname(String surname, Pageable pageable) {
        Slice<OutstandingPersonEntity> slice = repository.findBySurnameContainingIgnoreCaseAndIsDeletedFalse(surname,  pageable);
        return mapper.toSliceResponse(slice);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonSliceResponse findByNameAndSurname(String name, String surname, Pageable pageable) {
        Slice<OutstandingPersonEntity> slice = repository.findByNameContainingIgnoreCaseAndSurnameContainingIgnoreCaseAndIsDeletedFalse(name, surname, pageable);
        return mapper.toSliceResponse(slice);
    }

    @Override
    @Transactional
    public OutstandingPersonResponse create(OutstandingPersonCreateRequest outstandingPersonCreateRequest) {
        OutstandingPersonEntity outstandingPersonEntity =  mapper.toEntity(outstandingPersonCreateRequest);
        setAcademicDegreeFromRequest(outstandingPersonEntity, outstandingPersonCreateRequest.getAcademicDegree());
        setAcademicTitleFromRequest(outstandingPersonEntity, outstandingPersonCreateRequest.getAcademicTitle());
        setEducationSubjectFromRequest(outstandingPersonEntity, outstandingPersonCreateRequest.getEducationSubject());
        repository.save(outstandingPersonEntity);
        return mapper.toResponse(outstandingPersonEntity);
    }

    @Override
    @Transactional
    public OutstandingPersonResponse update(OutstandingPersonUpdateRequest outstandingPersonUpdateRequest, Long id) {
        OutstandingPersonEntity outstandingPersoneEntity = findOutstandingPersonById(id);
        mapper.updateEntity(outstandingPersoneEntity, outstandingPersonUpdateRequest);
        setAcademicDegreeFromRequest(outstandingPersoneEntity, outstandingPersonUpdateRequest.getAcademicDegree());
        setAcademicTitleFromRequest(outstandingPersoneEntity, outstandingPersonUpdateRequest.getAcademicTitle());
        setEducationSubjectFromRequest(outstandingPersoneEntity, outstandingPersonUpdateRequest.getEducationSubject());
        return mapper.toResponse(outstandingPersoneEntity);
    }

    @Override
    @Transactional
    public OutstandingPersonResponse recover(Long id) {
        OutstandingPersonEntity outstandingPersoneEntity = findDeletedOutstandingPersonById(id);
        outstandingPersoneEntity.setIsDeleted(false);
        return mapper.toResponse(outstandingPersoneEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Resource getPhoto(Long id) {
        OutstandingPersonEntity outstandingPersonEntity = findOutstandingPersonById(id);
        String photoLink = outstandingPersonEntity.getPhotoLink();
        if (photoLink == null || photoLink.isEmpty()) return null;
        try {
            Path filePath = Paths.get(photoLink);
            if (!Files.exists(filePath)) {
                throw new NotFoundException("Photo file not found: " + filePath);
            }
            return new FileSystemResource(filePath);
        } catch (Exception e) {
            throw new PhotoProcessingException("Error reading photo" + e);
        }
    }

    @Override
    @Transactional
    public PhotoResponse updatePhoto(Long id, MultipartFile photo) {
        OutstandingPersonEntity outstandingPersonEntity = findOutstandingPersonById(id);
        compreFaceService.deletePhotoExamples(id);
        compreFaceService.addPhotoExamples(id, photo);
        byte[] compressedPhoto;
        try {
            compressedPhoto = ImageOptimizer.compress(photo);
        } catch (IOException e) {
            throw new PhotoProcessingException("Failed to compress photo");
        }
        String extension = MediaType.parseMediaType(Objects.requireNonNull(photo.getContentType())).getSubtype();
        String fileName = "person_" + id + "." + extension;
        Path filePath = Paths.get(uploadDir).resolve(fileName);
        try {
            Files.write(filePath, compressedPhoto);
        } catch (IOException e) {
            throw new PhotoProcessingException("Failed to save photo");
        }
        outstandingPersonEntity.setPhotoLink(filePath.toString());
        outstandingPersonEntity.setContentType(photo.getContentType());
        return mapper.toPhotoResponse(outstandingPersonEntity);
    }

    @Override
    @Transactional
    public List<PersonResponse> recognizePhoto(MultipartFile photo) {
        List<RecognitionResponse> recognitionResponse = compreFaceService.recognizeFaces(photo);
        return recognitionResponse.stream()
                .filter(response -> response.getSimilarity() != null && response.getSimilarity() >= 0.7)
                .map(RecognitionResponse::getSubject)
                .map(subjectId -> {
                    try {
                        Long id = Long.parseLong(subjectId);
                        OutstandingPersonEntity person = findOutstandingPersonById(id);
                        return mapper.toSimpleResponse(person);
                    } catch (NumberFormatException e) {
                        throw new RecognitionException(e + " Invalid subject ID format: " + subjectId);
                    }
                })
                .toList();
    }

    private OutstandingPersonEntity findOutstandingPersonById(Long id) {
        return repository.findByIdAndIsDeletedFalse(id).orElseThrow(
                () -> new NotFoundException("Outstanding person not found with id: " + id)
        );
    }

    private OutstandingPersonEntity findDeletedOutstandingPersonById(Long id) {
        return repository.findByIdAndIsDeletedTrue(id).orElseThrow(
                () -> new NotFoundException(" Deleted outstanding person not found with id: " + id)
        );
    }

    private void setAcademicDegreeFromRequest(OutstandingPersonEntity entity, String academicDegreeName) {
        AcademicDegreeEntity academicDegree = academicDegreeRepository.findByNameIgnoreCaseAndIsDeletedFalse(academicDegreeName).orElseThrow(
                () -> new NotFoundException("Academic degree not found with name: " + academicDegreeName)
        );
        entity.setAcademicDegree(academicDegree);
    }

    private void setAcademicTitleFromRequest(OutstandingPersonEntity entity, String academicTitleName) {
        AcademicTitleEntity academicTitle = academicTitleRepository.findByNameIgnoreCaseAndIsDeletedFalse(academicTitleName).orElseThrow(
                () -> new NotFoundException("Academic title not found with name: " + academicTitleName)
        );
        entity.setAcademicTitle(academicTitle);
    }

    private void setEducationSubjectFromRequest(OutstandingPersonEntity entity, String educationSubjectName) {
        EducationSubjectEntity educationSubject = educationSubjectRepository.findByNameIgnoreCaseAndIsDeletedFalse(educationSubjectName).orElseThrow(
                () -> new NotFoundException("Education subject not found with name: " + educationSubjectName)
        );
        entity.setEducationSubject(educationSubject);
    }
}
