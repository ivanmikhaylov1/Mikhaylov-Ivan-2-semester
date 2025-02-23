package org.example.mikhaylovivan2semester.repository.implementations;

import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.repository.interfaces.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {
  private static final Map<UUID, Article> articles = new HashMap<>();
  private static final Map<UUID, Timestamp> userLastRequestTimes = new HashMap<>();
  private static final Map<UUID, List<UUID>> articleCategories = new HashMap<>();

  @Override
  public List<Article> getAllArticles() {
    return new ArrayList<>(articles.values());
  }

  @Override
  public void saveArticle(Article article) {
    articles.put(article.id(), article);
  }

  @Override
  public void updateUserLastRequestTime(UUID userId) {
    userLastRequestTimes.put(userId, new Timestamp(System.currentTimeMillis()));
  }

  @Override
  public Timestamp getUserLastRequestTime(UUID userId) {
    return userLastRequestTimes.getOrDefault(userId, null);
  }

  @Override
  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    articleCategories.put(articleId, Arrays.asList(catalogId, websiteId));
  }
}
