package org.example.mikhaylovivan2semester.util;

import java.util.UUID;

/**
 * Класс для создания уникальных ключей кэша
 */
public class CacheKeyGenerator {
  // Разделитель для составления ключей
  private static final String SEPARATOR = "::";

  /**
   * Создаёт ключ для хранения сайтов пользователя
   **/
  public static String getUserWebsitesKey(UUID userId) {
    return "websites" + SEPARATOR + "user" + SEPARATOR + userId;
  }

  /**
   * Создаёт ключ для проверки уникальности создания сайта
   **/
  public static String getWebsiteCreationKey(UUID userId, String name, String url) {
    return "website" + SEPARATOR + "create"
        + SEPARATOR + userId
        + SEPARATOR + name
        + SEPARATOR + url;
  }
}
