package org.example.mikhaylovivan2semester.util;

import java.util.UUID;

/**
 * Utility class for generating cache keys in a consistent and descriptive manner.
 * This class provides methods to create structured cache keys for different entities
 * and operations in the application.
 */
public class CacheKeyGenerator {
  private static final String DELIMITER = "::";

  /**
   * Generates a cache key for user-specific website operations.
   *
   * @param userId The ID of the user
   * @return A structured cache key in the format "websites::user::{userId}"
   */
  public static String generateUserWebsitesKey(UUID userId) {
    return String.format("websites%suser%s%s", DELIMITER, DELIMITER, userId);
  }

  /**
   * Generates a cache key for website creation idempotency check.
   *
   * @param userId The ID of the user
   * @param name   The name of the website
   * @param url    The URL of the website
   * @return A structured cache key in the format "website::create::{userId}::{name}::{url}"
   */
  public static String generateWebsiteCreationKey(UUID userId, String name, String url) {
    return String.format("website%screate%s%s%s%s%s%s",
        DELIMITER, DELIMITER, userId, DELIMITER, name, DELIMITER, url);
  }
}
