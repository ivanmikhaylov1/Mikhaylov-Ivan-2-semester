package org.example.mikhaylovivan2semester.repository;

import org.example.mikhaylovivan2semester.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.*;

public class ArticleRepository {
  private static final Logger logger = LoggerFactory.getLogger(ArticleRepository.class);
  private static final HashMap<UUID, Article> articles = new HashMap<>();
  private static final HashMap<UUID, Timestamp> userLastRequestTimes = new HashMap<>();
  private static final HashMap<UUID, List<UUID>> articleCategories = new HashMap<>();

  public List<Article> getAllArticles() {
    logger.info("Получены все статьи");
    return new ArrayList<>(articles.values());
  }

  public void saveArticle(Article article) {
    articles.put(article.id(), article);
    logger.info("Сохранена статья: {}", article);
  }

  public void updateUserLastRequestTime(UUID userId) {
    userLastRequestTimes.put(userId, new Timestamp(System.currentTimeMillis()));
    logger.info("Обновлено время последнего запроса для пользователя {}", userId);
  }

  public Timestamp getUserLastRequestTime(UUID userId) {
    return userLastRequestTimes.get(userId);
  }

  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    articleCategories.put(articleId, Arrays.asList(catalogId, websiteId));
    logger.info("Сохранены категории статьи {}: catalogId={}, websiteId={}", articleId, catalogId, websiteId);
  }
}
