package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.repository.interfaces.ArticleRepository;
import org.example.mikhaylovivan2semester.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {
  private final ArticleRepository articleRepository;

  @Autowired
  public ArticleServiceImpl(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @Override
  public List<Article> getAllArticles() {
    return articleRepository.getAllArticles();
  }

  @Override
  public void saveArticle(Article article) {
    articleRepository.saveArticle(article);
  }

  @Override
  public void updateUserLastRequestTime(UUID userId) {
    articleRepository.updateUserLastRequestTime(userId);
  }

  @Override
  public Timestamp getUserLastRequestTime(UUID userId) {
    return articleRepository.getUserLastRequestTime(userId);
  }

  @Override
  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    articleRepository.saveArticleCategory(articleId, catalogId, websiteId);
  }
}
