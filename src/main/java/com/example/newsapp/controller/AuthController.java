package com.example.newsapp.controller;

import com.example.newsapp.dto.AuthRequest;
import com.example.newsapp.dto.AuthResponse;
import com.example.newsapp.entity.User;
import com.example.newsapp.dto.LoginRequest;
import com.example.newsapp.dto.LoginResponse;
import com.example.newsapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import com.example.newsapp.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    // ✅ KEEP THIS
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
