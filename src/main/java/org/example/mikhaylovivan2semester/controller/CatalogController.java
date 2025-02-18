package org.example.mikhaylovivan2semester.controller;

import org.example.mikhaylovivan2semester.controller.apidocumentation.CatalogApiDocumentation;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.dto.Response;
import org.example.mikhaylovivan2semester.dto.request.CreateCatalogRequest;
import org.example.mikhaylovivan2semester.service.interfaces.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/catalogs")
public class CatalogController implements CatalogApiDocumentation {
  private final CatalogService catalogService;

  @Autowired
  public CatalogController(CatalogService catalogService) {
    this.catalogService = catalogService;
  }

  @Override
  @GetMapping("/basic")
  public ResponseEntity<Response<List<Catalog>>> getBasicCatalogs() {
    List<Catalog> catalogs = catalogService.getBasicCatalogs();
    return ResponseEntity.ok(new Response<>(catalogs));
  }

  @Override
  @GetMapping("/user/{userId}")
  public ResponseEntity<Response<List<Catalog>>> getUserCatalogs(@PathVariable UUID userId) {
    List<Catalog> catalogs = catalogService.getUserCatalogs(userId);
    return ResponseEntity.ok(new Response<>(catalogs));
  }

  @Override
  @GetMapping("/exists")
  public ResponseEntity<Response<Boolean>> existsByName(@RequestParam String name) {
    boolean exists = catalogService.existsByName(name);
    return ResponseEntity.ok(new Response<>(exists));
  }

  @Override
  @GetMapping("/name")
  public ResponseEntity<Response<Optional<Catalog>>> getByName(@RequestParam UUID userId, @RequestParam String name) {
    Optional<Catalog> catalog = catalogService.getByName(userId, name);
    return ResponseEntity.ok(new Response<>(catalog));
  }

  @Override
  @DeleteMapping("/delete")
  public ResponseEntity<Response<Void>> deleteByName(@RequestParam UUID userId, @RequestParam String name) {
    catalogService.deleteByName(userId, name);
    return ResponseEntity.ok(new Response<>(200, "Категория успешно удалена"));
  }

  @Override
  @PutMapping("/addToUser")
  public ResponseEntity<Response<Catalog>> addToUser(@RequestBody CreateCatalogRequest catalogRequest) {
    String name = catalogRequest.getName();
    Catalog catalog = catalogService.addToUser(catalogRequest.getUserId(), name);
    return ResponseEntity.status(201).body(new Response<>(catalog));
  }

  @Override
  @PutMapping("/addUserCatalog")
  public ResponseEntity<Response<Catalog>> addUserCatalog(@RequestBody CreateCatalogRequest catalogRequest) {
    String name = catalogRequest.getName();
    Catalog catalog = catalogService.addUserCatalog(catalogRequest.getUserId(), name);
    return ResponseEntity.status(201).body(new Response<>(catalog));
  }
}
