package org.example.mikhaylovivan2semester.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.repository.ArticleRepository;
import org.example.mikhaylovivan2semester.service.interfaces.ArticleService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
  private final ArticleRepository articleRepository;

  private ArticleServiceImpl(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public List<Article> getAllArticles() {
    log.info("Вызван метод getAllArticles в ArticleService");
    return articleRepository.getAllArticles();
  }

  @Override
  public void saveArticle(Article article) {
    log.info("Вызван метод saveArticle в ArticleService для статьи с ID: {}", article.id());
    articleRepository.saveArticle(article);
  }

  @Override
  public void updateUserLastRequestTime(UUID userId) {
    log.info("Вызван метод updateUserLastRequestTime в ArticleService для пользователя с ID: {}", userId);
    articleRepository.updateUserLastRequestTime(userId);
  }

  @Override
  public Timestamp getUserLastRequestTime(UUID userId) {
    log.info("Вызван метод getUserLastRequestTime в ArticleService для пользователя с ID: {}", userId);
    return articleRepository.getUserLastRequestTime(userId);
  }

  @Override
  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    log.info("Вызван метод saveArticleCategory в ArticleService для статьи с ID: {}, категории с ID: {}, и вебсайта с ID: {}", articleId, catalogId, websiteId);
    articleRepository.saveArticleCategory(articleId, catalogId, websiteId);
  }
}
