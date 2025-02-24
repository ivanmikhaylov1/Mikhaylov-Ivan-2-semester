package org.example.mikhaylovivan2semester.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public record Article(
    @NotNull UUID id,
    @NotBlank @Size(min = 3, max = 100) String name,
    @NotBlank @Size(min = 10, max = 500) String description,
    @NotBlank String date,
    @NotBlank String link
) {
}
