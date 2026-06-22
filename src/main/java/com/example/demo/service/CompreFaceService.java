package com.example.demo.service;

import com.example.demo.model.RecognitionResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CompreFaceService {
    void deletePhotoExamples(Long id);
    void addPhotoExamples(Long id, MultipartFile file);
    List<RecognitionResponse> recognizeFaces(MultipartFile file);
}
