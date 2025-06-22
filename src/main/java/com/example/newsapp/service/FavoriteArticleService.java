package com.example.newsapp.service;

import com.example.newsapp.entity.FavoriteArticle;
import com.example.newsapp.entity.User;

import java.util.List;

public interface FavoriteArticleService {
    FavoriteArticle save(FavoriteArticle article, User user);
    List<FavoriteArticle> getFavorites(User user);
    void deleteFavorite(Long articleId, User user);
}
