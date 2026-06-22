package com.example.demo.model;

import lombok.Data;

@Data
public class RecognitionResponse {
    private String subject;
    private Double similarity;
}
