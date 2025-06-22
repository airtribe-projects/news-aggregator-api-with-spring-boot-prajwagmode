package com.example.newsapp.controller;

import com.example.newsapp.entity.User;
import com.example.newsapp.model.NewsArticle;
import com.example.newsapp.repository.UserRepository;
import com.example.newsapp.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;
    private final UserRepository userRepository;

    @GetMapping("/fetch")
    public ResponseEntity<List<NewsArticle>> fetchNews(@RequestParam(required = false) String category,
                                                       @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Use default category from preferences if not provided
        if ((category == null || category.isEmpty()) && user.getPreferences() != null && !user.getPreferences().isEmpty()) {
            category = user.getPreferences().get(0).getCategory();
        }

        List<NewsArticle> news = newsService.fetchNews(category);
        return ResponseEntity.ok(news);
    }
}
