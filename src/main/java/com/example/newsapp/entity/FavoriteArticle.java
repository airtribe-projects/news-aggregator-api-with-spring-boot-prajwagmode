package com.example.newsapp.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "favorite_articles")
public class FavoriteArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String source;
    private String publishedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
