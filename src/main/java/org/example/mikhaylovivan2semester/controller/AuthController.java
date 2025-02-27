package org.example.mikhaylovivan2semester.controller;

import org.example.mikhaylovivan2semester.controller.apidocumentation.AuthControllerDocumentation;
import org.example.mikhaylovivan2semester.dto.Response;
import org.example.mikhaylovivan2semester.dto.request.create.CreateUserRequest;
import org.example.mikhaylovivan2semester.dto.request.LoginRequest;
import org.example.mikhaylovivan2semester.service.implementations.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocumentation {
  private final AuthServiceImpl authService;

  @Autowired
  public AuthController(AuthServiceImpl authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  @Override
  public ResponseEntity<Response<Object>> register(@RequestBody CreateUserRequest registerRequest) {
    try {
      var userDTO = authService.registerUser(registerRequest.getName(), registerRequest.getPassword());
      return ResponseEntity.ok(new Response<>(200, userDTO, "Регистрация успешна"));
    } catch (RuntimeException e) {
      return ResponseEntity.status(400).body(new Response<>(400, null, e.getMessage()));
    }
  }

  @PostMapping("/login")
  @Override
  public ResponseEntity<Response<String>> login(@RequestBody LoginRequest loginRequest) {
    try {
      String token = authService.authenticate(loginRequest.getName(), loginRequest.getPassword());
      return ResponseEntity.ok(new Response<>(200, token, "Вход успешно выполнен"));
    } catch (RuntimeException e) {
      return ResponseEntity.status(401).body(new Response<>(401, null, e.getMessage()));
    }
  }
}
