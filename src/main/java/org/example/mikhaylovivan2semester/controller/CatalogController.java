package org.example.mikhaylovivan2semester.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.mikhaylovivan2semester.controller.apidocumentation.CatalogApiDocumentation;
import org.example.mikhaylovivan2semester.dto.Response;
import org.example.mikhaylovivan2semester.dto.request.AddCatalogToUserRequest;
import org.example.mikhaylovivan2semester.dto.request.create.CreateCatalogRequest;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.service.interfaces.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/catalogs")
@Validated
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
  public ResponseEntity<Response<List<Catalog>>> getUserCatalogs(@PathVariable @NotNull UUID userId) {
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
  @GetMapping("/user/{userId}/by-name")
  public ResponseEntity<Response<Optional<Catalog>>> getByName(
      @PathVariable UUID userId,
      @RequestParam String name
  ) {
    Optional<Catalog> catalog = catalogService.getByName(userId, name);
    return ResponseEntity.ok(new Response<>(catalog));
  }

  @Override
  @DeleteMapping("/delete")
  public ResponseEntity<Response<Void>> deleteByName(
      @RequestParam @NotNull UUID userId,
      @RequestParam @NotBlank String name
  ) {
    catalogService.deleteByName(userId, name);
    return ResponseEntity.ok(new Response<>(200, "Категория успешно удалена"));
  }

  @Override
  @PostMapping("/create")
  public ResponseEntity<Response<Catalog>> createCatalog(@RequestBody CreateCatalogRequest request) {
    Catalog catalog = catalogService.createCatalog(request.getUserId(), request.getName());
    return ResponseEntity.status(201).body(new Response<>(catalog));
  }

  @PutMapping("/addToUser")
  @Override
  public ResponseEntity<Response<Catalog>> addToUser(@RequestBody @Valid AddCatalogToUserRequest request) {
    Catalog catalog = catalogService.addToUser(request.getUserId(), request.getName());
    return ResponseEntity.status(201).body(new Response<>(catalog));
  }
}
