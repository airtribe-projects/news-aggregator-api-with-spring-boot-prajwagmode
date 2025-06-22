package com.example.newsapp.service.impl;

import com.example.newsapp.entity.FavoriteArticle;
import com.example.newsapp.entity.User;
import com.example.newsapp.repository.FavoriteArticleRepository;
import com.example.newsapp.service.FavoriteArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteArticleServiceImpl implements FavoriteArticleService {

    private final FavoriteArticleRepository repository;

    @Override
    public FavoriteArticle save(FavoriteArticle article, User user) {
        article.setUser(user);
        return repository.save(article);
    }

    @Override
    public List<FavoriteArticle> getFavorites(User user) {
        return repository.findByUser(user);
    }

    @Override
    public void deleteFavorite(Long articleId, User user) {
        repository.deleteByIdAndUser(articleId, user);
    }
}
