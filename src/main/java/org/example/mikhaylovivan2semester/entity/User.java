package org.example.mikhaylovivan2semester.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record User(
    UUID id,
    @NotBlank @Size(min = 3, max = 50) String name,
    @NotBlank @Size(min = 6) String password
) {}
