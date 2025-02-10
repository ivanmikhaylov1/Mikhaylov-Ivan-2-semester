package org.example.mikhaylovivan2semester.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class CatalogRepository {
  private static final HashMap<UUID, Catalog> catalogs = new HashMap<>();

  public CatalogRepository() {
    save(new Catalog(UUID.randomUUID(), "Новости", null));
    save(new Catalog(UUID.randomUUID(), "Технологии", null));
    save(new Catalog(UUID.randomUUID(), "Спорт", null));
  }

  public Optional<Catalog> getByName(UUID userId, String name) {
    log.info("Поиск каталога по имени: {} для userId: {}", name, userId);
    return catalogs.values().stream()
        .filter(c -> c.name().equals(name) && (c.userId() == null || c.userId().equals(userId)))
        .findFirst();
  }

  public void deleteByName(UUID userId, String name) {
    log.info("Удаление каталога: {} для userId: {}", name, userId);
    catalogs.entrySet().removeIf(entry ->
        entry.getValue().name().equals(name) &&
            (entry.getValue().userId() == null || entry.getValue().userId().equals(userId))
    );
  }

  public Catalog addToUser(UUID userId, String name) {
    log.info("Добавление каталога {} для userId: {}", name, userId);
    Catalog catalog = new Catalog(UUID.randomUUID(), name, userId);
    catalogs.put(catalog.catalogId(), catalog);
    return catalog;
  }

  public Catalog addUserCatalog(UUID userId, String name) {
    return addToUser(userId, name);
  }

  public boolean existsByName(String name) {
    log.info("Проверка существования каталога {}", name);
    return catalogs.values().stream().anyMatch(catalog -> catalog.name().equalsIgnoreCase(name));
  }

  public List<Catalog> getBasicCatalogs() {
    log.info("Получение базовых каталогов");
    return catalogs.values().stream()
        .filter(c -> c.userId() == null)
        .collect(Collectors.toList());
  }

  public List<Catalog> getUserCatalogs(UUID userId) {
    log.info("Получение каталогов пользователя userId: {}", userId);
    return catalogs.values().stream()
        .filter(c -> c.userId() != null && c.userId().equals(userId))
        .collect(Collectors.toList());
  }

  public void save(Catalog catalog) {
    catalogs.put(catalog.catalogId(), catalog);
    log.info("Сохранен каталог: {}", catalog);
  }
}
