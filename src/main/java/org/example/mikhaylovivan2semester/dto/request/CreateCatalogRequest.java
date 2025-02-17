package org.example.mikhaylovivan2semester.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreateCatalogRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String name;

  private final UUID userId;

  public CreateCatalogRequest(String name, UUID userId) {
    this.name = name;
    this.userId = userId;
  }
}
