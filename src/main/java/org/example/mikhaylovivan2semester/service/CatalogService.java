package org.example.mikhaylovivan2semester.service;

import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.repository.CatalogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogService {
  private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);
  private final CatalogRepository catalogRepository = new CatalogRepository();

  public Optional<Catalog> getByName(UUID userId, String name) {
    logger.info("Вызван метод getByName для пользователя с ID: {} и категории: {}", userId, name);
    return catalogRepository.getByName(userId, name);
  }

  public void deleteByName(UUID userId, String name) {
    logger.info("Вызван метод deleteByName для пользователя с ID: {} и категории: {}", userId, name);
    catalogRepository.deleteByName(userId, name);
  }

  public Catalog addToUser(UUID userId, String name) {
    logger.info("Вызван метод addToUser для пользователя с ID: {} и категории: {}", userId, name);
    return catalogRepository.addToUser(userId, name);
  }

  public Catalog addUserCatalog(UUID userId, String name) {
    logger.info("Вызван метод addUserCatalog для пользователя с ID: {} и категории: {}", userId, name);
    return catalogRepository.addUserCatalog(userId, name);
  }

  public boolean existsByName(UUID userId, String name) {
    logger.info("Вызван метод existsByName для пользователя с ID: {} и категории: {}", userId, name);
    return catalogRepository.existsByName(userId, name);
  }

  public List<Catalog> getBasicCatalogs() {
    logger.info("Вызван метод getBasicCatalogs");
    return catalogRepository.getBasicCatalogs();
  }

  public List<Catalog> getUserCatalogs(UUID userId) {
    logger.info("Вызван метод getUserCatalogs для пользователя с ID: {}", userId);
    return catalogRepository.getUserCatalogs(userId);
  }
}
