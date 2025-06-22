package com.example.newsapp.service.impl;

import com.example.newsapp.entity.User;
import com.example.newsapp.entity.UserPreference;
import com.example.newsapp.repository.UserPreferencesRepository;
import com.example.newsapp.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PreferenceServiceImpl implements PreferenceService {

    private final UserPreferencesRepository userPreferenceRepository;

    @Override
    public List<UserPreference> getPreferenceByUser(User user) {
        return userPreferenceRepository.findByUser(user);
    }

    @Override
    public UserPreference updatePreference(UserPreference preference, User user) {
        userPreferenceRepository.deleteByUser(user); // Replace old
        preference.setUser(user);
        return userPreferenceRepository.save(preference);
    }

    // ✅ This was missing
    @Override
    public List<String> getPreferences(User user) {
        return userPreferenceRepository.findByUser(user).stream()
                .map(UserPreference::getCategory)
                .collect(Collectors.toList());
    }
}
