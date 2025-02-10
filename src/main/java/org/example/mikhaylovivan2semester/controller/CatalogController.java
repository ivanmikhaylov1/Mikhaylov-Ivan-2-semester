package org.example.mikhaylovivan2semester.controller;

import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.service.CatalogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {
  private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
  private final CatalogService catalogService;

  public CatalogController(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @GetMapping("/basic")
  public List<Catalog> getBasicCatalogs() {
    logger.info("Получен запрос на получение всех стандартных каталогов");
    return catalogService.getBasicCatalogs();
  }

  @GetMapping("/user/{userId}")
  public List<Catalog> getUserCatalogs(@PathVariable UUID userId) {
    logger.info("Получен запрос на получение всех пользовательских каталогов для пользователя с ID: {}", userId);
    return catalogService.getUserCatalogs(userId);
  }

  @GetMapping("/exists")
  public boolean existsByName(@RequestParam String name) {
    logger.info("Получен запрос на проверку существования категории по названию c именем: {}", name);
    return catalogService.existsByName(name);
  }

  @GetMapping("/name")
  public Optional<Catalog> getByName(@RequestParam UUID userId, @RequestParam String name) {
    logger.info("Получен запрос на получение категории по имени для пользователя с ID: {} и именем: {}", userId, name);
    return catalogService.getByName(userId, name);
  }

  @PutMapping("/delete")
  public void deleteByName(@RequestParam UUID userId, @RequestParam String name) {
    logger.info("Получен запрос на удаление категории по имени для пользователя с ID: {} и именем: {}", userId, name);
    catalogService.deleteByName(userId, name);
  }

  @PutMapping("/addToUser")
  public Catalog addToUser(@RequestParam UUID userId, @RequestParam String name) {
    logger.info("Получен запрос на добавление категории к пользователю с ID: {} и именем категории: {}", userId, name);
    return catalogService.addToUser(userId, name);
  }

  @PutMapping("/addUserCatalog")
  public Catalog addUserCatalog(@RequestParam UUID userId, @RequestParam String name) {
    logger.info("Получен запрос на добавление пользовательской категории для пользователя с ID: {} и именем категории: {}", userId, name);
    return catalogService.addUserCatalog(userId, name);
  }
}
