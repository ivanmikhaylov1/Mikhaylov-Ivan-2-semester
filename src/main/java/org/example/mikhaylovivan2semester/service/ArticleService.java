package org.example.mikhaylovivan2semester.service;

import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class ArticleService {
  private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
  private final ArticleRepository articleRepository = new ArticleRepository();

  public List<Article> getAllArticles() {
    logger.info("Вызван метод getAllArticles в ArticleService");
    return articleRepository.getAllArticles();
  }

  public void saveArticle(Article article) {
    logger.info("Вызван метод saveArticle в ArticleService для статьи с ID: {}", article.id());
    articleRepository.saveArticle(article);
  }

  public void updateUserLastRequestTime(UUID userId) {
    logger.info("Вызван метод updateUserLastRequestTime в ArticleService для пользователя с ID: {}", userId);
    articleRepository.updateUserLastRequestTime(userId);
  }

  public Timestamp getUserLastRequestTime(UUID userId) {
    logger.info("Вызван метод getUserLastRequestTime в ArticleService для пользователя с ID: {}", userId);
    return articleRepository.getUserLastRequestTime(userId);
  }


  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    logger.info("Вызван метод saveArticleCategory в ArticleService для статьи с ID: {}, категории с ID: {}, и вебсайта с ID: {}", articleId, catalogId, websiteId);
    articleRepository.saveArticleCategory(articleId, catalogId, websiteId);
  }
}
