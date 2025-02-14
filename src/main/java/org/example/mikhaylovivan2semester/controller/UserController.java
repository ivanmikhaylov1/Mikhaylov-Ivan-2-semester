package org.example.mikhaylovivan2semester.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.api.UserApiDocumentation;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.entity.Request;
import org.example.mikhaylovivan2semester.entity.Response;
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
public class UserController implements UserApiDocumentation {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Override
  @GetMapping
  public ResponseEntity<Response<List<UserDTO>>> findAll() {
    log.info("Получен запрос на получение всех пользователей");
    List<UserDTO> users = userService.findAll();
    return ResponseEntity.ok(new Response<>(users));
  }

  @Override
  @PostMapping
  public ResponseEntity<Response<UserDTO>> save(@RequestBody Request<CreateUserRequest> userRequest) {
    CreateUserRequest createUserRequest = userRequest.data();
    log.info("Получен запрос на сохранение пользователя: {}", createUserRequest.name());
    Optional<UserDTO> user = userService.save(createUserRequest.name(), createUserRequest.password());
    return user.map(value -> ResponseEntity.status(201).body(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.badRequest().body(new Response<>(400, "Не удалось создать пользователя")));
  }

  @Override
  @GetMapping("/{userId}")
  public ResponseEntity<Response<UserDTO>> getById(@PathVariable UUID userId) {
    log.info("Получен запрос на получение пользователя по ID: {}", userId);
    Optional<UserDTO> user = userService.getById(userId);
    return user.map(value -> ResponseEntity.ok(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Пользователь не найден")));
  }

  @Override
  @GetMapping("/by-name")
  public ResponseEntity<Response<UserDTO>> findByName(@RequestParam @NotBlank String name) {
    log.info("Получен запрос на получение пользователя по имени: {}", name);
    Optional<UserDTO> user = userService.findByName(name);
    return user.map(value -> ResponseEntity.ok(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Пользователь не найден")));
  }

  @Override
  @GetMapping("/exists")
  public ResponseEntity<Response<Boolean>> exists(@RequestParam @NotBlank String name) {
    log.info("Получен запрос на проверку существования пользователя с именем: {}", name);
    boolean exists = userService.exists(name);
    return ResponseEntity.ok(new Response<>(exists));
  }

  // Вспомогательный класс для сохранения данных пользователя
  public static class CreateUserRequest {
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 6)
    private String password;

    public CreateUserRequest(String name, String password) {
      this.name = name;
      this.password = password;
    }

    public String name() {
      return name;
    }

    public String password() {
      return password;
    }
  }
}
