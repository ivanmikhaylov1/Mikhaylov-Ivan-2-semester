package org.example.mikhaylovivan2semester.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.repository.CatalogRepository;
import org.example.mikhaylovivan2semester.service.interfaces.CatalogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {
  private final CatalogRepository catalogRepository;

  private CatalogServiceImpl(CatalogRepository catalogRepository) {
    this.catalogRepository = catalogRepository;
  }

  @Override
  public Optional<Catalog> getByName(UUID userId, String name) {
    log.info("Вызван метод getByName для пользователя с ID: {} и категории: {}", userId, name);
    return catalogRepository.getByName(userId, name);
  }

  @Override
  public void deleteByName(UUID userId, String name) {
    log.info("Вызван метод deleteByName для пользователя с ID: {} и категории: {}", userId, name);
    catalogRepository.deleteByName(userId, name);
  }

  @Override
  public Catalog addToUser(UUID userId, String name) {
    log.info("Вызван метод addToUser для пользователя с ID: {} и категории: {}", userId, name);
    return catalogRepository.addToUser(userId, name);
  }

  @Override
  public Catalog addUserCatalog(UUID userId, String name) {
    log.info("Вызван метод addUserCatalog для пользователя с ID: {} и категории: {}", userId, name);
    return catalogRepository.addUserCatalog(userId, name);
  }

  @Override
  public boolean existsByName(String name) {
    log.info("Вызван метод existsByName для пользователя с ID: {} и категории: {}", name);
    return catalogRepository.existsByName(name);
  }

  @Override
  public List<Catalog> getBasicCatalogs() {
    log.info("Вызван метод getBasicCatalogs");
    return catalogRepository.getBasicCatalogs();
  }

  @Override
  public List<Catalog> getUserCatalogs(UUID userId) {
    log.info("Вызван метод getUserCatalogs для пользователя с ID: {}", userId);
    return catalogRepository.getUserCatalogs(userId);
  }
}
