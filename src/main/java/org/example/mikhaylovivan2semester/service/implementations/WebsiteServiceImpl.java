package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.exception.WebsiteOperationException;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.example.mikhaylovivan2semester.repository.WebsiteRepository;
import org.example.mikhaylovivan2semester.service.interfaces.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WebsiteServiceImpl implements WebsiteService {

  private final WebsiteRepository websiteRepository;
  private final UserRepository userRepository;

  @Autowired
  public WebsiteServiceImpl(WebsiteRepository websiteRepository, UserRepository userRepository) {
    this.websiteRepository = websiteRepository;
    this.userRepository = userRepository;
  }

  @Override
  @Cacheable("basicWebsites")
  public List<Website> getBasicWebsites() {
    return websiteRepository.findBasicWebsites();
  }

  @Override
  @Cacheable(value = "userWebsites", key = "#userId")
  public List<Website> getUserWebsites(UUID userId) {
    return userRepository.findById(userId)
        .map(websiteRepository::findByUser)
        .orElse(List.of());
  }

  @Override
  public boolean existsByName(String name) {
    return websiteRepository.existsByName(name);
  }

  @Override
  public Optional<Website> getByName(UUID userId, String name) {
    return userRepository.findById(userId)
        .map(user -> websiteRepository.findByUserAndName(user, name))
        .orElse(Optional.empty());
  }

  @Override
  @CacheEvict(value = "userWebsites", key = "#userId")
  public Website addUserWebsite(UUID userId, String name, String url) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new WebsiteOperationException("Пользователь не найден"));

    return websiteRepository.addUserWebsite(user, name, url);
  }

  @Override
  @CacheEvict(value = "userWebsites", key = "#userId")
  public boolean deleteByName(UUID userId, String name) {
    userRepository.findById(userId)
        .ifPresent(user -> websiteRepository.deleteByUserAndName(user, name));
    return true;
  }
}