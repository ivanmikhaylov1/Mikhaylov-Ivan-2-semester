package org.example.mikhaylovivan2semester.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.entity.Website;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class WebsiteRepository {
  private static final HashMap<UUID, Website> websites = new HashMap<>();

  public WebsiteRepository() {
    save(new Website(UUID.randomUUID(), "Google", "https://google.com", null));
    save(new Website(UUID.randomUUID(), "YouTube", "https://youtube.com", null));
  }

  public List<Website> getBasicWebsites() {
    log.info("Получение базовых сайтов");
    return websites.values().stream()
        .filter(website -> website.userId() == null)
        .collect(Collectors.toList());
  }

  public List<Website> getUserWebsites(UUID userId) {
    log.info("Получение сайтов пользователя userId: {}", userId);
    return websites.values().stream()
        .filter(website -> userId.equals(website.userId()))
        .collect(Collectors.toList());
  }

  public boolean existsByName(String name) {
    log.info("Проверка существования сайта с именем: {}", name);
    return websites.values().stream().anyMatch(website -> website.name().equalsIgnoreCase(name));
  }

  public Optional<Website> getByName(UUID userId, String name) {
    log.info("Поиск сайта '{}' для userId: {}", name, userId);
    return websites.values().stream()
        .filter(website -> website.name().equalsIgnoreCase(name) &&
            (website.userId() == null || website.userId().equals(userId)))
        .findFirst();
  }

  public Website addUserWebsite(UUID userId, String name, String url) {
    log.info("Добавление сайта '{}' (URL: {}) для userId: {}", name, url, userId);
    Website website = new Website(UUID.randomUUID(), name, url, userId);
    websites.put(website.id(), website);
    return website;
  }

  public void deleteByName(UUID userId, String name) {
    log.info("Удаление сайта '{}' для userId: {}", name, userId);
    websites.entrySet().removeIf(entry ->
        entry.getValue().name().equalsIgnoreCase(name) &&
            (entry.getValue().userId() == null || entry.getValue().userId().equals(userId)));
  }

  public void save(Website website) {
    websites.put(website.id(), website);
    log.info("Сохранен сайт: {}", website);
  }
}
