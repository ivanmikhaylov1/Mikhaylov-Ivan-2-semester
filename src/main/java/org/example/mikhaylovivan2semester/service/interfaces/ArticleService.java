package org.example.mikhaylovivan2semester.service.interfaces;

import org.example.mikhaylovivan2semester.entity.Article;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public interface ArticleService {
  List<Article> getAllArticles();

  void saveArticle(Article article);

  CompletableFuture<Void> updateUserLastRequestTime(UUID userId);

  Timestamp getUserLastRequestTime(UUID userId);

  CompletableFuture<Void> saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId);
}
