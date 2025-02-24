package org.example.mikhaylovivan2semester.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record Catalog(
    @NotNull UUID catalogId,
    @NotBlank @Size(min = 3, max = 20) String name,
    UUID userId
) {
}

