package org.example.mikhaylovivan2semester.service.interfaces;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.mikhaylovivan2semester.entity.Catalog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogService {
  Optional<Catalog> getByName(UUID userId, String name);

  void deleteByName(UUID userId, String name);

  Catalog createCatalog(UUID userId, String name);

  boolean existsByName(String name);

  List<Catalog> getBasicCatalogs();

  List<Catalog> getUserCatalogs(UUID userId);

  Catalog addToUser(@NotNull UUID userId, @NotBlank String name);
}
