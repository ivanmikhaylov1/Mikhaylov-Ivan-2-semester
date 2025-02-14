package org.example.mikhaylovivan2semester.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.api.WebsiteApiDocumentation;
import org.example.mikhaylovivan2semester.entity.Response;
import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.service.interfaces.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/websites")
@Validated
public class WebsiteController implements WebsiteApiDocumentation {
  private final WebsiteService websiteService;

  @Autowired
  public WebsiteController(WebsiteService websiteService) {
    this.websiteService = websiteService;
  }

  @Override
  @GetMapping("/basic")
  public ResponseEntity<Response<List<Website>>> getBasicWebsites() {
    log.info("Получен запрос на получение базовых сайтов");
    List<Website> websites = websiteService.getBasicWebsites();
    return ResponseEntity.ok(new Response<>(websites));
  }

  @Override
  @GetMapping("/user/{userId}")
  public ResponseEntity<Response<List<Website>>> getUserWebsites(@PathVariable UUID userId) {
    log.info("Получен запрос на получение сайтов пользователя с ID: {}", userId);
    List<Website> websites = websiteService.getUserWebsites(userId);
    return ResponseEntity.ok(new Response<>(websites));
  }

  @Override
  @PostMapping("/user/{userId}")
  public ResponseEntity<Response<Website>> addUserWebsite(
      @PathVariable UUID userId,
      @NotBlank @Size(min = 3, max = 100) @RequestParam String name,
      @NotBlank @Size(min = 5, max = 200) @RequestParam String url) {
    log.info("Получен запрос на добавление сайта '{}' для пользователя с ID: {}", name, userId);
    Website website = websiteService.addUserWebsite(userId, name, url);
    return ResponseEntity.status(201).body(new Response<>(website));
  }

  @Override
  @DeleteMapping("/user/{userId}")
  public ResponseEntity<Response<Void>> deleteByName(
      @PathVariable UUID userId,
      @NotBlank @RequestParam String name) {
    log.info("Получен запрос на удаление сайта '{}' у пользователя с ID: {}", name, userId);
    boolean deleted = websiteService.deleteByName(userId, name);
    if (deleted) {
      return ResponseEntity.ok(new Response<>(200, "Сайт успешно удалён"));
    } else {
      return ResponseEntity.status(404).body(new Response<>(404, "Сайт не найден"));
    }
  }

  @Override
  @GetMapping("/exists")
  public ResponseEntity<Response<Boolean>> existsByName(@RequestParam @NotBlank String name) {
    log.info("Получен запрос на проверку существования сайта с именем: {}", name);
    boolean exists = websiteService.existsByName(name);
    return ResponseEntity.ok(new Response<>(exists));
  }

  @Override
  @GetMapping("/user/{userId}/by-name")
  public ResponseEntity<Response<Website>> getByName(
      @PathVariable UUID userId,
      @NotBlank @RequestParam String name) {
    log.info("Получен запрос на получение сайта '{}' для пользователя с ID: {}", name, userId);
    Optional<Website> website = websiteService.getByName(userId, name);
    return website
        .map(w -> ResponseEntity.ok(new Response<>(w)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Сайт не найден")));
  }
}
