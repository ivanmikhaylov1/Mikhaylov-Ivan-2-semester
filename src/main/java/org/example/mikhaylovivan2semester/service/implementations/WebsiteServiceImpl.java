package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.repository.WebsiteRepository;
import org.example.mikhaylovivan2semester.service.interfaces.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WebsiteServiceImpl implements WebsiteService {
  private final WebsiteRepository websiteRepository;

  @Autowired
  private WebsiteServiceImpl(WebsiteRepository websiteRepository) {
    this.websiteRepository = websiteRepository;
  }

  @Override
  public List<Website> getBasicWebsites() {
    return websiteRepository.getBasicWebsites();
  }

  @Override
  public List<Website> getUserWebsites(UUID userId) {
    return websiteRepository.getUserWebsites(userId);
  }

  @Override
  public boolean existsByName(String name) {
    return websiteRepository.existsByName(name);
  }

  @Override
  public Optional<Website> getByName(UUID userId, String name) {
    return websiteRepository.getByName(userId, name);
  }

  @Override
  public Website addUserWebsite(UUID userId, String name, String url) {
    return websiteRepository.addUserWebsite(userId, name, url);
  }

  @Override
  public boolean deleteByName(UUID userId, String name) {
    websiteRepository.deleteByName(userId, name);
    return false;
  }
}
