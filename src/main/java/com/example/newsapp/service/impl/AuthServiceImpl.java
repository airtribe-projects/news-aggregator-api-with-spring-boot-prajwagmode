package com.example.newsapp.service.impl;

import com.example.newsapp.dto.AuthRequest;
import com.example.newsapp.dto.AuthResponse;
import com.example.newsapp.dto.LoginRequest;
import com.example.newsapp.dto.LoginResponse;
import com.example.newsapp.dto.RegisterRequest;

import com.example.newsapp.entity.Role;
import com.example.newsapp.entity.User;

import com.example.newsapp.repository.UserRepository;
import com.example.newsapp.security.JwtService;
import com.example.newsapp.service.AuthService;
import com.example.newsapp.exception.EmailAlreadyExistsException;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        // Basic logic, you can customize later
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtService.generateToken(user);

        return new LoginResponse(token);
    }


    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }
}
