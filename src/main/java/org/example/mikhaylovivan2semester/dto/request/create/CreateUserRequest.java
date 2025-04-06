package org.example.mikhaylovivan2semester.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateUserRequest {
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
}
