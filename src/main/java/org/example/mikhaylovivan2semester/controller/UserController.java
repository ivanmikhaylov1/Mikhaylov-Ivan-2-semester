package org.example.mikhaylovivan2semester.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.mikhaylovivan2semester.controller.apidocumentation.UserApiDocumentation;
import org.example.mikhaylovivan2semester.dto.Response;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.dto.request.create.CreateUserRequest;
import org.example.mikhaylovivan2semester.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    List<UserDTO> users = userService.findAll();
    return ResponseEntity.ok(new Response<>(users));
  }

  @Override
  @PostMapping
  public ResponseEntity<Response<UserDTO>> save(@RequestBody CreateUserRequest createUserRequest) {
    if (createUserRequest == null) {
      return ResponseEntity.badRequest().body(new Response<>(400, "Некорректные данные запроса"));
    }

    Optional<UserDTO> user = userService.save(createUserRequest.getName(), createUserRequest.getPassword());
    return user.map(value -> ResponseEntity.status(201).body(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.badRequest().body(new Response<>(400, "Не удалось создать пользователя")));
  }

  @Override
  @GetMapping("/{userId}")
  public ResponseEntity<Response<UserDTO>> getById(@PathVariable UUID userId) {
    Optional<UserDTO> user = userService.getById(userId);
    return user.map(value -> ResponseEntity.ok(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Пользователь не найден")));
  }

  @Override
  @GetMapping("/by-name")
  public ResponseEntity<Response<UserDTO>> findByName(@RequestParam @NotBlank String name) {
    Optional<UserDTO> user = userService.findByName(name);
    return user.map(value -> ResponseEntity.ok(new Response<>(value)))
        .orElseGet(() -> ResponseEntity.status(404).body(new Response<>(404, "Пользователь не найден")));
  }

  @Override
  @GetMapping("/exists")
  public ResponseEntity<Response<Boolean>> exists(@RequestParam @NotBlank String name) {
    boolean exists = userService.exists(name);
    return ResponseEntity.ok(new Response<>(exists));
  }
}
