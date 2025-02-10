package org.example.mikhaylovivan2semester.repository;

import org.example.mikhaylovivan2semester.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UserRepository {
  private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
  private static final HashMap<UUID, User> users = new HashMap<>();

  public List<User> findAll() {
    logger.info("Получены все пользователи");
    return new ArrayList<>(users.values());
  }

  public User save(String name, String password) {
    User user = new User(UUID.randomUUID(), name, password);
    users.put(user.id(), user);
    logger.info("Сохранен пользователь: {}", user);
    return user;
  }

  public Optional<User> getById(UUID userId) {
    logger.info("Поиск пользователя по ID: {}", userId);
    return Optional.ofNullable(users.get(userId));
  }

  public Optional<User> findByName(String name) {
    logger.info("Поиск пользователя по имени: {}", name);
    return users.values().stream()
        .filter(user -> user.name().equals(name))
        .findFirst();
  }

  public boolean exists(String name) {
    logger.info("Проверка существования пользователя с именем: {}", name);
    return users.values().stream().anyMatch(user -> user.name().equals(name));
  }

  public void delete(UUID userId) {
    users.remove(userId);
    logger.info("Удален пользователь с ID: {}", userId);
  }
}
