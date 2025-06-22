package com.example.newsapp.service;

import com.example.newsapp.model.NewsArticle;
import java.util.List;

public interface NewsService {
    List<NewsArticle> fetchNews(String category);
}
