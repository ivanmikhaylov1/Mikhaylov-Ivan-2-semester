package org.example.mikhaylovivan2semester.repository.implementations;

import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.repository.interfaces.CatalogRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CatalogRepositoryImpl implements CatalogRepository {
  private static final HashMap<UUID, Catalog> catalogs = new HashMap<>();

  public CatalogRepositoryImpl() {
    save(new Catalog(UUID.randomUUID(), "Новости", null));
    save(new Catalog(UUID.randomUUID(), "Технологии", null));
    save(new Catalog(UUID.randomUUID(), "Спорт", null));
  }

  @Override
  public Optional<Catalog> getByName(UUID userId, String name) {
    return catalogs.values().stream()
        .filter(c -> c.name().equals(name) && (c.userId() == null || c.userId().equals(userId)))
        .findFirst();
  }

  @Override
  public void deleteByName(UUID userId, String name) {
    catalogs.entrySet().removeIf(entry ->
        entry.getValue().name().equals(name) &&
            (entry.getValue().userId() == null || entry.getValue().userId().equals(userId))
    );
  }

  @Override
  public Catalog addToUser(UUID userId, String name) {
    Catalog catalog = new Catalog(UUID.randomUUID(), name, userId);
    catalogs.put(catalog.catalogId(), catalog);
    return catalog;
  }

  @Override
  public Catalog addUserCatalog(UUID userId, String name) {
    return addToUser(userId, name);
  }

  @Override
  public boolean existsByName(String name) {
    return catalogs.values().stream().anyMatch(catalog -> catalog.name().equalsIgnoreCase(name));
  }

  @Override
  public List<Catalog> getBasicCatalogs() {
    return catalogs.values().stream()
        .filter(c -> c.userId() == null)
        .collect(Collectors.toList());
  }

  @Override
  public List<Catalog> getUserCatalogs(UUID userId) {
    return catalogs.values().stream()
        .filter(c -> c.userId() != null && c.userId().equals(userId))
        .collect(Collectors.toList());
  }

  @Override
  public void save(Catalog catalog) {
    catalogs.put(catalog.catalogId(), catalog);
  }
}
