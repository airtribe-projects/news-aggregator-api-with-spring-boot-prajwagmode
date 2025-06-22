package com.example.newsapp.repository;

import com.example.newsapp.entity.FavoriteArticle;
import com.example.newsapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteArticleRepository extends JpaRepository<FavoriteArticle, Long> {
    List<FavoriteArticle> findByUser(User user);
    void deleteByIdAndUser(Long id, User user);
}
