package org.example.mikhaylovivan2semester.entity;

import java.util.UUID;

public record Website(UUID id, String name, String url, UUID userId) {
}