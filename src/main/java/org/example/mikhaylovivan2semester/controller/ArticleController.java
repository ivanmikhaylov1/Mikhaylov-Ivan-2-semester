package org.example.mikhaylovivan2semester.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.api.ArticleApiDocumentation;
import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.entity.Request;
import org.example.mikhaylovivan2semester.entity.Response;
import org.example.mikhaylovivan2semester.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/articles")
@Validated
public class ArticleController implements ArticleApiDocumentation {
  private final ArticleService articleService;

  @Autowired
  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @Override
  @GetMapping
  public ResponseEntity<Response<List<Article>>> getNewArticles() {
    log.info("Получен запрос на получение всех статей");
    List<Article> articles = articleService.getAllArticles();
    return ResponseEntity.ok(new Response<>(articles));
  }

  @Override
  @PostMapping
  public ResponseEntity<Response<String>> saveArticle(@Valid @RequestBody Request<Article> articleRequest) {
    Article article = articleRequest.data();
    log.info("Получен запрос на сохранение статьи: {}", article.name());
    articleService.saveArticle(article);
    return ResponseEntity.status(201).body(new Response<>(201, "Статья успешно сохранена"));
  }

  @Override
  @PutMapping("/user/{userId}/lastRequestTime")
  public ResponseEntity<Response<String>> updateUserLastRequestTime(@PathVariable UUID userId) {
    log.info("Получен запрос на обновление последнего времени запроса от пользователя: {}", userId);
    articleService.updateUserLastRequestTime(userId);
    return ResponseEntity.ok(new Response<>(200, "Время последнего запроса обновлено"));
  }

  @Override
  @GetMapping("/user/{userId}/lastRequestTime")
  public ResponseEntity<Response<Date>> getUserLastRequestTime(@PathVariable UUID userId) {
    log.info("Получен запрос на получение последнего времени запроса от пользователя: {}", userId);
    Date lastRequestTime = articleService.getUserLastRequestTime(userId);

    if (lastRequestTime != null) {
      return ResponseEntity.ok(new Response<>(lastRequestTime));
    } else {
      log.warn("Последнее время запроса не найдено для пользователя: {}", userId);
      return ResponseEntity.status(404).body(new Response<>(404, "Последнее время запроса не найдено"));
    }
  }

  @Override
  @PostMapping("/user/category")
  public ResponseEntity<Response<String>> saveArticleCategory(
      @NotNull @RequestParam UUID articleId,
      @NotNull @RequestParam UUID catalogId,
      @NotNull @RequestParam UUID websiteId) {
    log.info("Получен запрос на сохранение категории статьи: articleId={}, catalogId={}, websiteId={}",
        articleId, catalogId, websiteId);
    articleService.saveArticleCategory(articleId, catalogId, websiteId);
    return ResponseEntity.status(201).body(new Response<>(201, "Категория статьи сохранена"));
  }
}
