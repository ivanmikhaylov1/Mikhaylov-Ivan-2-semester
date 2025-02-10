package org.example.mikhaylovivan2semester.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.entity.Response;
import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Response<List<User>>> findAll() {
    log.info("Получен запрос на получение всех пользователей");
    List<User> users = userService.findAll();
    return ResponseEntity.ok(new Response<>(users));
  }

  @PostMapping
  public ResponseEntity<Response<User>> save(
      @NotBlank @Size(min = 3, max = 50) @RequestParam String name,
      @NotBlank @Size(min = 6) @RequestParam String password) {
    log.info("Получен запрос на сохранение пользователя: {}", name);
    Optional<User> user = userService.save(name, password);
    return user.map(value -> ResponseEntity.status(201).body(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.badRequest().body(new Response<>(400, "Не удалось создать пользователя")));
  }

  @GetMapping("/{userId}")
  public ResponseEntity<Response<User>> getById(@PathVariable UUID userId) {
    log.info("Получен запрос на получение пользователя по ID: {}", userId);
    Optional<User> user = userService.getById(userId);
    return user.map(value -> ResponseEntity.ok(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Пользователь не найден")));
  }

  @GetMapping("/by-name")
  public ResponseEntity<Response<User>> findByName(@RequestParam @NotBlank String name) {
    log.info("Получен запрос на получение пользователя по имени: {}", name);
    Optional<User> user = userService.findByName(name);
    return user.map(value -> ResponseEntity.ok(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Пользователь не найден")));
  }

  @GetMapping("/exists")
  public ResponseEntity<Response<Boolean>> exists(@RequestParam @NotBlank String name) {
    log.info("Получен запрос на проверку существования пользователя с именем: {}", name);
    boolean exists = userService.exists(name);
    return ResponseEntity.ok(new Response<>(exists));
  }
}
