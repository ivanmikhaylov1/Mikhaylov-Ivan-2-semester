package org.example.mikhaylovivan2semester.controller;

import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/articles")
public class ArticleController {
  private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
  private final ArticleService articleService = new ArticleService();

  @GetMapping
  public List<Article> getNewArticles() {
    logger.info("Получен запрос на получение всех статей");
    return articleService.getAllArticles();
  }

  @PostMapping
  public void saveArticle(@RequestBody Article article) {
    logger.info("Получен запрос на сохранение статьи");
    articleService.saveArticle(article);
  }

  @PutMapping("/user/{userId}/lastRequestTime")
  public void updateUserLastRequestTime(@PathVariable UUID userId) {
    logger.info("Получен запрос на обновление последнего времени запроса от пользователя");
    articleService.updateUserLastRequestTime(userId);
  }

  @GetMapping("/user/{userId}/lastRequestTime")
  public Date getUserLastRequestTime(@PathVariable  UUID userId) {
    logger.info("Получен запрос на получение последнего времени запроса от пользователя");
    return articleService.getUserLastRequestTime(userId);
  }

  @PostMapping("/user/category")
  public void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId) {
    logger.info("Получен запрос на сохранение категории статьи");
    articleService.saveArticleCategory(articleId, catalogId, websiteId);
  }
}
