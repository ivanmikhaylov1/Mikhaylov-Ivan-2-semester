package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.repository.ArticleRepository;
import org.example.mikhaylovivan2semester.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
  @Cacheable("articles")
  public List<Article> getAllArticles() {
    return articleRepository.findAll();
  }

  @Override
  @CacheEvict(value = "articles", allEntries = true)
  @Transactional
  public void saveArticle(Article article) {
    articleRepository.save(article);
  }

  @Override
  @Transactional
  public void updateUserLastRequestTime(UUID userId) {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    articleRepository.updateUserLastRequestTime(userId, currentTime);
  }

  @Override
  public Timestamp getUserLastRequestTime(UUID userId) {
    return articleRepository.getUserLastRequestTime(userId);
  }

  @Override
  @Transactional
  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    articleRepository.saveArticleCategory(articleId, catalogId, websiteId);
  }
}
