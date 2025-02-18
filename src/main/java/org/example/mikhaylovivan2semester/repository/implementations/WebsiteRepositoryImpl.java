package org.example.mikhaylovivan2semester.repository.implementations;

import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.repository.interfaces.WebsiteRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class WebsiteRepositoryImpl implements WebsiteRepository {
  private static final HashMap<UUID, Website> websites = new HashMap<>();

  public WebsiteRepositoryImpl() {
    save(new Website(UUID.randomUUID(), "Google", "https://google.com", null));
    save(new Website(UUID.randomUUID(), "YouTube", "https://youtube.com", null));
  }

  @Override
  public List<Website> getBasicWebsites() {
    return websites.values().stream()
        .filter(website -> website.userId() == null)
        .collect(Collectors.toList());
  }

  @Override
  public List<Website> getUserWebsites(UUID userId) {
    return websites.values().stream()
        .filter(website -> userId.equals(website.userId()))
        .collect(Collectors.toList());
  }

  @Override
  public boolean existsByName(String name) {
    return websites.values().stream().anyMatch(website -> website.name().equalsIgnoreCase(name));
  }

  @Override
  public Optional<Website> getByName(UUID userId, String name) {
    return websites.values().stream()
        .filter(website -> website.name().equalsIgnoreCase(name) &&
            (website.userId() == null || website.userId().equals(userId)))
        .findFirst();
  }

  @Override
  public Website addUserWebsite(UUID userId, String name, String url) {
    Website website = new Website(UUID.randomUUID(), name, url, userId);
    websites.put(website.id(), website);
    return website;
  }

  @Override
  public void deleteByName(UUID userId, String name) {
    websites.entrySet().removeIf(entry ->
        entry.getValue().name().equalsIgnoreCase(name) &&
            (entry.getValue().userId() == null || entry.getValue().userId().equals(userId)));
  }

  @Override
  public void save(Website website) {
    websites.put(website.id(), website);
  }
}
