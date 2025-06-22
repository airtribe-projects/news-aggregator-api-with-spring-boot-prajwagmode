package com.example.newsapp.service.impl;

import com.example.newsapp.model.NewsArticle;
import com.example.newsapp.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${news.api.key}")
    private String apiKey;

    @Override
    public List<NewsArticle> fetchNews(String category) {
        // Use category as a keyword fallback (or use a default if null)
        String keyword = (category != null && !category.isEmpty()) ? category : "latest";

        // Build the NewsAPI URL (using everything endpoint for better results)
        String url = "https://newsapi.org/v2/everything?q=" + keyword + "&sortBy=publishedAt&language=en&apiKey=" + apiKey;

        // Fetch and parse response
        String response = restTemplate.getForObject(url, String.class);
        System.out.println("NewsAPI URL: " + url);
        System.out.println("Response snippet: " + response.substring(0, Math.min(500, response.length()))); // Limit output

        JSONObject json = new JSONObject(response);
        JSONArray articles = json.getJSONArray("articles");

        List<NewsArticle> newsList = new ArrayList<>();
        for (int i = 0; i < articles.length(); i++) {
            JSONObject articleJson = articles.getJSONObject(i);
            NewsArticle article = new NewsArticle();
            article.setTitle(articleJson.optString("title"));
            article.setDescription(articleJson.optString("description"));
            article.setUrl(articleJson.optString("url"));
            article.setUrlToImage(articleJson.optString("urlToImage"));
            article.setSourceName(articleJson.optJSONObject("source").optString("name"));
            newsList.add(article);
        }

        return newsList;
    }
}
