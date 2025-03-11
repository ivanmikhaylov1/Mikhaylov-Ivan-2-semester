package org.example.mikhaylovivan2semester.service.implementations;

import jakarta.transaction.Transactional;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.exception.CatalogOperationException;
import org.example.mikhaylovivan2semester.repository.CatalogRepository;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.example.mikhaylovivan2semester.service.interfaces.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogServiceImpl implements CatalogService {
  private final CatalogRepository catalogRepository;

  @Autowired
  public CatalogServiceImpl(CatalogRepository catalogRepository, UserRepository userRepository) {
    this.catalogRepository = catalogRepository;
  }

  @Override
  public Optional<Catalog> getByName(UUID userId, String name) {
    return catalogRepository.findByUserIdAndName(userId, name);
  }

  @Override
  @CacheEvict(value = "userCatalogs", key = "#userId")
  public void deleteByName(UUID userId, String name) {
    catalogRepository.deleteByUserIdAndName(userId, name);
  }

  @Override
  @Retryable(retryFor = CatalogOperationException.class,
      maxAttempts = 5,
      backoff = @Backoff(delay = 10000))
  @CacheEvict(value = "userCatalogs", key = "#userId")
  public Catalog createCatalog(UUID userId, String name) {
    if (catalogRepository.existsByName(name)) {
      throw new CatalogOperationException("Каталог с таким именем уже существует");
    }

    Catalog catalog = new Catalog(UUID.randomUUID(), name, userId);
    return catalogRepository.save(catalog);
  }

  @Override
  public boolean existsByName(String name) {
    return catalogRepository.existsByName(name);
  }

  @Override
  @Cacheable("basicCatalogs")
  public List<Catalog> getBasicCatalogs() {
    return catalogRepository.findBasicCatalogs();
  }

  @Override
  @Cacheable(value = "userCatalogs", key = "#userId")
  public List<Catalog> getUserCatalogs(UUID userId) {
    return catalogRepository.findByUserId(userId);
  }

  @Override
  @Transactional
  public Catalog addToUser(UUID userId, String name) {
    Catalog catalog = new Catalog(UUID.randomUUID(), name, userId);
    return catalogRepository.save(catalog);
  }
}
