package com.example.demo.controller.request.achievements;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchievementsUpdateRequest extends AchievementsCreateRequest{
    private Long id;
}
