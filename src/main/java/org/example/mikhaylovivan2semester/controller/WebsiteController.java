package org.example.mikhaylovivan2semester.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.mikhaylovivan2semester.controller.apidocumentation.WebsiteApiDocumentation;
import org.example.mikhaylovivan2semester.dto.Response;
import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.dto.request.CreateWebsiteRequest;
import org.example.mikhaylovivan2semester.service.interfaces.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    List<Website> websites = websiteService.getBasicWebsites();
    return ResponseEntity.ok(new Response<>(websites));
  }

  @Override
  @GetMapping("/user/{userId}")
  public ResponseEntity<Response<List<Website>>> getUserWebsites(@PathVariable UUID userId) {
    List<Website> websites = websiteService.getUserWebsites(userId);
    return ResponseEntity.ok(new Response<>(websites));
  }

  @Override
  @PostMapping("/user/{userId}")
  public ResponseEntity<Response<Website>> addUserWebsite(@RequestBody CreateWebsiteRequest createWebsiteRequest) {
    Website website = websiteService.addUserWebsite(
        createWebsiteRequest.getUserId(),
        createWebsiteRequest.getName(),
        createWebsiteRequest.getUrl());

    return ResponseEntity.status(201).body(new Response<>(website));
  }


  @Override
  @DeleteMapping("/user/{userId}")
  public ResponseEntity<Response<Void>> deleteByName(
      @PathVariable UUID userId,
      @NotBlank @RequestParam String name) {
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
    boolean exists = websiteService.existsByName(name);
    return ResponseEntity.ok(new Response<>(exists));
  }

  @Override
  @GetMapping("/user/{userId}/by-name")
  public ResponseEntity<Response<Website>> getByName(
      @PathVariable UUID userId,
      @NotBlank @RequestParam String name) {
    Optional<Website> website = websiteService.getByName(userId, name);
    return website
        .map(w -> ResponseEntity.ok(new Response<>(w)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Сайт не найден")));
  }
}
