package org.example.mikhaylovivan2semester.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.entity.Article;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.*;

@Slf4j
@Repository
public class ArticleRepository {
  private static final Map<UUID, Article> articles = new HashMap<>();
  private static final Map<UUID, Timestamp> userLastRequestTimes = new HashMap<>();
  private static final Map<UUID, List<UUID>> articleCategories = new HashMap<>();

  public List<Article> getAllArticles() {
    log.info("Получены все статьи");
    return new ArrayList<>(articles.values());
  }

  public void saveArticle(Article article) {
    articles.put(article.id(), article);
    log.info("Сохранена статья: {}", article);
  }

  public void updateUserLastRequestTime(UUID userId) {
    userLastRequestTimes.put(userId, new Timestamp(System.currentTimeMillis()));
    log.info("Обновлено время последнего запроса для пользователя {}", userId);
  }

  public Timestamp getUserLastRequestTime(UUID userId) {
    return userLastRequestTimes.getOrDefault(userId, null);
  }

  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    articleCategories.put(articleId, Arrays.asList(catalogId, websiteId));
    log.info("Сохранены категории статьи {}: catalogId={}, websiteId={}", articleId, catalogId, websiteId);
  }
}
