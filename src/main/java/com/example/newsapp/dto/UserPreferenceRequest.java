package com.example.newsapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class UserPreferenceRequest {

    @NotEmpty(message = "Preferences list cannot be empty")
    private List<String> preferences;
}
