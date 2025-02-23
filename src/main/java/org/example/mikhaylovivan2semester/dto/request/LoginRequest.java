package org.example.mikhaylovivan2semester.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class LoginRequest {
  @NotBlank
  private String name;

  @NotBlank
  private String password;

  public LoginRequest(String name, String password) {
    this.name = name;
    this.password = password;
  }
}
