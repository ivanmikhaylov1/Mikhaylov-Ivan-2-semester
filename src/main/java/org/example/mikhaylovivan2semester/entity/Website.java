package org.example.mikhaylovivan2semester.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

public record Website(
    @NotNull UUID id,
    @NotBlank String name,
    @URL @Size(min = 5, max = 30) String url,
    UUID userId
) {
}
