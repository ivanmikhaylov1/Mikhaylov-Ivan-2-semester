package org.example.mikhaylovivan2semester.service;

import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.repository.WebsiteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class WebsiteService {
  private final WebsiteRepository websiteRepository = new WebsiteRepository();

  public List<Website> getBasicWebsites() {
    return websiteRepository.getBasicWebsites();
  }

  public List<Website> getUserWebsites(UUID userId) {
    return websiteRepository.getUserWebsites(userId);
  }

  public boolean existsByName(String name) {
    return websiteRepository.existsByName(name);
  }

  public Optional<Website> getByName(UUID userId, String name) {
    return websiteRepository.getByName(userId, name);
  }

  public Website addUserWebsite(UUID userId, String name, String url) {
    return websiteRepository.addUserWebsite(userId, name, url);
  }

  public void deleteByName(UUID userId, String name) {
    websiteRepository.deleteByName(userId, name);
  }
}
