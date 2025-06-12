package com.example.demo.controller.request.achievements;

import lombok.Data;

@Data
public class AchievementsCreateRequest {
    private String title;
    private int year;
    private String description;

    // зависимости
    private Long personId;
}
