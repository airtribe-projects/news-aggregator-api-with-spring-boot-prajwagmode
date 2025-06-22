package com.example.newsapp.service;

import com.example.newsapp.dto.AuthResponse;
import com.example.newsapp.dto.AuthRequest;
import com.example.newsapp.dto.LoginRequest;
import com.example.newsapp.dto.LoginResponse; // ✅ Add this import
import com.example.newsapp.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    LoginResponse login(LoginRequest request);
}
