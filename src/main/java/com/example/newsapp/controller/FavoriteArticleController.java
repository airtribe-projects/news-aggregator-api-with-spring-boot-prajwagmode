package com.example.newsapp.controller;

import com.example.newsapp.entity.FavoriteArticle;
import com.example.newsapp.entity.User;
import com.example.newsapp.repository.UserRepository;
import com.example.newsapp.service.FavoriteArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteArticleController {

    private final FavoriteArticleService favoriteArticleService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<FavoriteArticle> addFavorite(@RequestBody FavoriteArticle article,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(favoriteArticleService.save(article, user));
    }

    @GetMapping
    public ResponseEntity<List<FavoriteArticle>> getFavorites(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(favoriteArticleService.getFavorites(user));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Long id,
                                               @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        favoriteArticleService.deleteFavorite(id, user);
        return ResponseEntity.noContent().build();
    }
}
