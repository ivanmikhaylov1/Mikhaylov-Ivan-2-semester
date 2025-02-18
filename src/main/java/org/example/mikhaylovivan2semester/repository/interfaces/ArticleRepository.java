package org.example.mikhaylovivan2semester.repository.interfaces;

import org.example.mikhaylovivan2semester.entity.Article;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface ArticleRepository {
  List<Article> getAllArticles();

  void saveArticle(Article article);

  void updateUserLastRequestTime(UUID userId);

  Timestamp getUserLastRequestTime(UUID userId);

  void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId);
}
