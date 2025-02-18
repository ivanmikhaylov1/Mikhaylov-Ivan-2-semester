package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.repository.interfaces.CatalogRepository;
import org.example.mikhaylovivan2semester.service.interfaces.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatalogServiceImpl implements CatalogService {
  private final CatalogRepository catalogRepository;

  @Autowired
  public CatalogServiceImpl(CatalogRepository catalogRepository) {
    this.catalogRepository = catalogRepository;
  }

  @Override
  public Optional<Catalog> getByName(UUID userId, String name) {
    return catalogRepository.getByName(userId, name);
  }

  @Override
  public void deleteByName(UUID userId, String name) {
    catalogRepository.deleteByName(userId, name);
  }

  @Override
  public Catalog addToUser(UUID userId, String name) {
    return catalogRepository.addToUser(userId, name);
  }

  @Override
  public Catalog addUserCatalog(UUID userId, String name) {
    return catalogRepository.addUserCatalog(userId, name);
  }

  @Override
  public boolean existsByName(String name) {
    return catalogRepository.existsByName(name);
  }

  @Override
  public List<Catalog> getBasicCatalogs() {
    return catalogRepository.getBasicCatalogs();
  }

  @Override
  public List<Catalog> getUserCatalogs(UUID userId) {
    return catalogRepository.getUserCatalogs(userId);
  }
}
