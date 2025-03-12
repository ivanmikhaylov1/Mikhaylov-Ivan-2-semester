package org.example.mikhaylovivan2semester.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateCatalogRequest {
  @NotNull
  private UUID userId;

  @NotBlank
  @Size(min = 3, max = 20)
  private String name;
}
