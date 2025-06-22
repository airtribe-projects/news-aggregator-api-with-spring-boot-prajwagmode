package com.example.newsapp.service;

import com.example.newsapp.entity.UserPreference;

public interface UserPreferencesService {
    UserPreference getPreferences(String email);
    UserPreference updatePreferences(String email, UserPreference newPrefs);
}
