package com.example.demo.controller.request.publications;

import lombok.Data;

@Data
public class PublicationsCreateRequest {
    private String title;
    private int year;
    private String link;

    // зависимости
    private Long personId;
}
