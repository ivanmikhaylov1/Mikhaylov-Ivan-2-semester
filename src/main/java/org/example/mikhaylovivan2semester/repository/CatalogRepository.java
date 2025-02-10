package org.example.mikhaylovivan2semester.repository;

import org.example.mikhaylovivan2semester.entity.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogRepository {
  private static final Logger logger = LoggerFactory.getLogger(CatalogRepository.class);
  private static final HashMap<UUID, Catalog> catalogs = new HashMap<>();

  public CatalogRepository() {
    save(new Catalog(UUID.randomUUID(), "Новости", null));
    save(new Catalog(UUID.randomUUID(), "Технологии", null));
    save(new Catalog(UUID.randomUUID(), "Спорт", null));
  }

  public Optional<Catalog> getByName(UUID userId, String name) {
    logger.info("Поиск каталога по имени: {} для userId: {}", name, userId);
    return catalogs.values().stream()
        .filter(c -> c.name().equals(name) && (c.userId() == null || c.userId().equals(userId)))
        .findFirst();
  }

  public void deleteByName(UUID userId, String name) {
    logger.info("Удаление каталога: {} для userId: {}", name, userId);
    catalogs.entrySet().removeIf(entry ->
        entry.getValue().name().equals(name) &&
            (entry.getValue().userId() == null || entry.getValue().userId().equals(userId))
    );
  }

  public Catalog addToUser(UUID userId, String name) {
    logger.info("Добавление каталога {} для userId: {}", name, userId);
    Catalog catalog = new Catalog(UUID.randomUUID(), name, userId);
    catalogs.put(catalog.catalogId(), catalog);
    return catalog;
  }

  public Catalog addUserCatalog(UUID userId, String name) {
    return addToUser(userId, name);
  }

  public boolean existsByName(String name) {
    logger.info("Проверка существования каталога {}", name);
    return catalogs.values().stream().anyMatch(catalog -> catalog.name().equalsIgnoreCase(name));
  }

  public List<Catalog> getBasicCatalogs() {
    logger.info("Получение базовых каталогов");
    return catalogs.values().stream()
        .filter(c -> c.userId() == null)
        .collect(Collectors.toList());
  }

  public List<Catalog> getUserCatalogs(UUID userId) {
    logger.info("Получение каталогов пользователя userId: {}", userId);
    return catalogs.values().stream()
        .filter(c -> c.userId() != null && c.userId().equals(userId))
        .collect(Collectors.toList());
  }

  public void save(Catalog catalog) {
    catalogs.put(catalog.catalogId(), catalog);
    logger.info("Сохранен каталог: {}", catalog);
  }
}
