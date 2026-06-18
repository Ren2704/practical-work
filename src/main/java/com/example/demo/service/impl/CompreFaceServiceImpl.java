package com.example.demo.service.impl;

import com.example.demo.exceptions.CompreFaceException;
import com.example.demo.service.CompreFaceService;
import com.example.model.RecognitionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompreFaceServiceImpl implements CompreFaceService {
    private final WebClient webClient;

    @Override
    @Transactional
    public void deletePhotoExamples(Long id) {
        webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("recognition/faces")
                        .queryParam("subject", id)
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
                        .path("recognition/faces")
                        .queryParam("subject", id.toString())
                        .queryParam("det_prob_threshold", 0.7)
                        .build())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file.getResource()))
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
        String responses = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("recognition/recognize")
                        .queryParam("limit", 1)
                        .queryParam("prediction_count", 5)
                        .queryParam("det_prob_threshold", 0.75)
                        .build())
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", file.getResource()))
                .retrieve()
                .onStatus(
                        HttpStatusCode::isError,
                        response -> response.bodyToMono(String.class)
                                .map(body -> new CompreFaceException("CompreFace error: " + body))
                )
                .bodyToMono(CompreFaceResponse.class)
                .block();

        return parseResponse(response);
    }
}
