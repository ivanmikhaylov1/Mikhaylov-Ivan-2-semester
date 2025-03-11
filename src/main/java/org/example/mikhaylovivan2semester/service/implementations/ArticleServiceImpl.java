package org.example.mikhaylovivan2semester.service.implementations;

import jakarta.transaction.Transactional;
import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.repository.ArticleRepository;
import org.example.mikhaylovivan2semester.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ArticleServiceImpl implements ArticleService {

  private final ArticleRepository articleRepository;
  private final WebClient webClient;

  @Autowired
  public ArticleServiceImpl(ArticleRepository articleRepository, WebClient.Builder webClientBuilder) {
    this.articleRepository = articleRepository;
    this.webClient = webClientBuilder.baseUrl("https://api.example.com").build();
  }

  @Override
  @Cacheable(value = "articles", key = "'all'")
  public List<Article> getAllArticles() {
    return articleRepository.findAll();
  }

  @Override
  @CacheEvict(value = "articles", key = "#article.id")
  @Transactional
  public void saveArticle(Article article) {
    articleRepository.save(article);
  }

  @Async
  @Transactional
  @Override
  public CompletableFuture<Void> updateUserLastRequestTime(UUID userId) {
    Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    articleRepository.updateUserLastRequestTime(userId, currentTime);
    return CompletableFuture.completedFuture(null);
  }

  @Cacheable(value = "userRequests", key = "#userId")
  @Override
  public Timestamp getUserLastRequestTime(UUID userId) {
    return articleRepository.getUserLastRequestTime(userId);
  }

  @Async
  @Transactional
  @Override
  public CompletableFuture<Void> saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    articleRepository.saveArticleCategory(articleId, catalogId, websiteId);
    return CompletableFuture.completedFuture(null);
  }

  @Async
  public CompletableFuture<String> callExternalService() {
    return webClient.get()
        .uri("/external/articles")
        .retrieve()
        .bodyToMono(String.class)
        .doOnError(error -> System.err.println("WebClient error: " + error.getMessage()))
        .toFuture();
  }

  public String callExternalServiceBlocking() {
    return webClient.get()
        .uri("/external/articles")
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }
}
