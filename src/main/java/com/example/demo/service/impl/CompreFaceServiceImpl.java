package com.example.demo.service.impl;

import com.example.demo.constants.CompreFace;
import com.example.demo.exceptions.CompreFaceException;
import com.example.demo.mapper.CompreFaceMapper;
import com.example.demo.model.CompreFaceResponse;
import com.example.demo.model.RecognitionResponse;
import com.example.demo.service.CompreFaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompreFaceServiceImpl implements CompreFaceService {
    private final WebClient webClient;
    private final CompreFaceMapper mapper;

    @Override
    @Transactional
    public void deletePhotoExamples(Long id) {
        webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path(CompreFace.FACES_PATH)
                        .queryParam(CompreFace.SUBJECT_PARAM, id)
                        .build())
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(body -> new CompreFaceException("CompreFace error: " + body))
                )
                .toBodilessEntity()
                .block();
    }

    @Override
    @Transactional
    public void addPhotoExamples(Long id, MultipartFile file) {
        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(CompreFace.FACES_PATH)
                        .queryParam(CompreFace.SUBJECT_PARAM, id.toString())
                        .queryParam(CompreFace.SIMILARITY_PARAM, CompreFace.SIMILARITY_VALUE)
                        .build())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(CompreFace.FILE_FORM_FIELD, file.getResource()))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(body -> new CompreFaceException("CompreFace error: " + body))
                )
                .toBodilessEntity()
                .block();
    }

    @Override
    @Transactional
    public List<RecognitionResponse> recognizeFaces(MultipartFile file) {
        CompreFaceResponse compreFaceResponse = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path(CompreFace.RECOGNIZE_PATH)
                        .queryParam(CompreFace.LIMIT_FACES_IN_PHOTO, CompreFace.LIMIT_VALUE)
                        .queryParam(CompreFace.PREDICTION_COUNT, CompreFace.PREDICTION_VALUE)
                        .queryParam(CompreFace.SIMILARITY_PARAM, CompreFace.SIMILARITY_VALUE)
                        .build())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(CompreFace.FILE_FORM_FIELD, file.getResource()))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(body -> new CompreFaceException("CompreFace error: " + body))
                )
                .bodyToMono(CompreFaceResponse.class)
                .block();

        return Objects.requireNonNull(compreFaceResponse).getResult().stream()
                .filter(result -> result.getSubjects() != null)
                .flatMap(result -> result.getSubjects().stream())
                .map(mapper::toRecognitionResponse)
                .toList();
    }
}
