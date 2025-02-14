package org.example.mikhaylovivan2semester.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UserDTO(
    UUID id,
    @NotBlank @Size(min = 3, max = 50) String name
) {}
