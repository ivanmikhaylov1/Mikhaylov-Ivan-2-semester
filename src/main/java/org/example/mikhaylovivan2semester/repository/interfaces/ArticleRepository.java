package org.example.mikhaylovivan2semester.repository.interfaces;

import org.example.mikhaylovivan2semester.entity.Article;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository {
  List<Article> getAllArticles();

  void saveArticle(Article article);

  void updateUserLastRequestTime(UUID userId);

  Timestamp getUserLastRequestTime(UUID userId);

  void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId);
}
