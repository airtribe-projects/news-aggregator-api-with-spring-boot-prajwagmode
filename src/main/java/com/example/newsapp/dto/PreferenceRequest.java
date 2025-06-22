package com.example.newsapp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class PreferenceRequest {
    @NotEmpty(message = "Preference list cannot be empty")
    private List<String> categories;
}
