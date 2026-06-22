package com.example.demo.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
public class CompreFaceResponse {
    private List<Result> result = Collections.emptyList();
    
    @Data
    public static class Result {
        private Box box;
        private List<Subject> subjects;
    }
    
    @Data
    public static class Box {
        private Double probability;
        private Integer xMax;
        private Integer yMax;
        private Integer xMin;
        private Integer yMin;
    }
    
    @Data
    public static class Subject {
        private String subject;
        private Double similarity;
    }
}
