package org.example.mikhaylovivan2semester.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddCatalogToUserRequest {
  @NotNull
  private UUID userId;

  @NotBlank
  private String name;
}