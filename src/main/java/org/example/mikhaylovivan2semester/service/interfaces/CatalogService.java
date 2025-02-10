package org.example.mikhaylovivan2semester.service.interfaces;

import org.example.mikhaylovivan2semester.entity.Catalog;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatalogService {
  Optional<Catalog> getByName(UUID userId, String name);
  void deleteByName(UUID userId, String name);
  Catalog addToUser(UUID userId, String name);
  Catalog addUserCatalog(UUID userId, String name);
  boolean existsByName(String name);
  List<Catalog> getBasicCatalogs();
  List<Catalog> getUserCatalogs(UUID userId);
}
