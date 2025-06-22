package com.example.newsapp.service;

import com.example.newsapp.dto.PreferenceRequest;
import com.example.newsapp.entity.User;
import com.example.newsapp.entity.UserPreference;

import java.util.List;

public interface PreferenceService {

    List<UserPreference> getPreferenceByUser(User user);

    UserPreference updatePreference(UserPreference preference, User user);

    List<String> getPreferences(User user); // ✅ Make sure this is declared
}
