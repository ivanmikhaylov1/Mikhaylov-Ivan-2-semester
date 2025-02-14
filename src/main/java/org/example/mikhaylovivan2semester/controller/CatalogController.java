package org.example.mikhaylovivan2semester.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.api.CatalogApiDocumentation;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.entity.Request;
import org.example.mikhaylovivan2semester.entity.Response;
import org.example.mikhaylovivan2semester.service.implementations.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/catalogs")
public class CatalogController implements CatalogApiDocumentation {
  private final CatalogServiceImpl catalogService;

  @Autowired
  public CatalogController(CatalogServiceImpl catalogService) {
    this.catalogService = catalogService;
  }

  @Override
  @GetMapping("/basic")
  public ResponseEntity<Response<List<Catalog>>> getBasicCatalogs() {
    log.info("Получен запрос на получение всех стандартных каталогов");
    List<Catalog> catalogs = catalogService.getBasicCatalogs();
    return ResponseEntity.ok(new Response<>(catalogs));
  }

  @Override
  @GetMapping("/user/{userId}")
  public ResponseEntity<Response<List<Catalog>>> getUserCatalogs(@PathVariable UUID userId) {
    log.info("Получен запрос на получение всех пользовательских каталогов для пользователя с ID: {}", userId);
    List<Catalog> catalogs = catalogService.getUserCatalogs(userId);
    return ResponseEntity.ok(new Response<>(catalogs));
  }

  @Override
  @GetMapping("/exists")
  public ResponseEntity<Response<Boolean>> existsByName(@RequestParam String name) {
    log.info("Получен запрос на проверку существования категории по названию с именем: {}", name);
    boolean exists = catalogService.existsByName(name);
    return ResponseEntity.ok(new Response<>(exists));
  }

  @Override
  @GetMapping("/name")
  public ResponseEntity<Response<Optional<Catalog>>> getByName(@RequestParam UUID userId, @RequestParam String name) {
    log.info("Получен запрос на получение категории по имени для пользователя с ID: {} и именем: {}", userId, name);
    Optional<Catalog> catalog = catalogService.getByName(userId, name);
    return ResponseEntity.ok(new Response<>(catalog));
  }

  @Override
  @DeleteMapping("/delete")
  public ResponseEntity<Response<Void>> deleteByName(@RequestParam UUID userId, @RequestParam String name) {
    log.info("Получен запрос на удаление категории по имени для пользователя с ID: {} и именем: {}", userId, name);
    catalogService.deleteByName(userId, name);
    return ResponseEntity.ok(new Response<>(200, "Категория успешно удалена"));
  }

  @Override
  @PutMapping("/addToUser")
  public ResponseEntity<Response<Catalog>> addToUser(@RequestBody Request<String> catalogRequest) {
    String name = catalogRequest.data();
    log.info("Получен запрос на добавление категории к пользователю с ID: {} и именем категории: {}", catalogRequest.data(), name);
    Catalog catalog = catalogService.addToUser(UUID.fromString(catalogRequest.data()), name);
    return ResponseEntity.status(201).body(new Response<>(catalog));
  }

  @Override
  @PutMapping("/addUserCatalog")
  public ResponseEntity<Response<Catalog>> addUserCatalog(@RequestBody Request<String> catalogRequest) {
    String name = catalogRequest.data();
    log.info("Получен запрос на добавление пользовательской категории для пользователя с ID: {} и именем категории: {}", catalogRequest.data(), name);
    Catalog catalog = catalogService.addUserCatalog(UUID.fromString(catalogRequest.data()), name);
    return ResponseEntity.status(201).body(new Response<>(catalog));
  }
}
