package org.example.mikhaylovivan2semester.service.interfaces;

import org.example.mikhaylovivan2semester.entity.Website;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WebsiteService {
  List<Website> getBasicWebsites();

  List<Website> getUserWebsites(UUID userId);

  boolean existsByName(String name);

  Optional<Website> getByName(UUID userId, String name);

  Website addUserWebsite(UUID userId, String name, String url);

  boolean deleteByName(UUID userId, String name);
}
