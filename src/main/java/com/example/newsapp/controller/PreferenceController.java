package com.example.newsapp.controller;

import com.example.newsapp.entity.UserPreference;
import com.example.newsapp.entity.User;
import com.example.newsapp.repository.UserRepository;
import com.example.newsapp.service.PreferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preferences")
@RequiredArgsConstructor
public class PreferenceController {

    private final PreferenceService preferenceService;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserPreference>> getPreferences(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(preferenceService.getPreferenceByUser(user));
    }

    @PutMapping
    public ResponseEntity<UserPreference> updatePreferences(@RequestBody UserPreference preference,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(preferenceService.updatePreference(preference, user));
    }
}
