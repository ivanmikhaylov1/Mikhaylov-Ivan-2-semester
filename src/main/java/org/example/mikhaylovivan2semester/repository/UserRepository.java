package org.example.mikhaylovivan2semester.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class UserRepository {
  private static final HashMap<UUID, User> users = new HashMap<>();

  public List<User> findAll() {
    log.info("Получены все пользователи");
    return new ArrayList<>(users.values());
  }

  public User save(String name, String password) {
    User user = new User(UUID.randomUUID(), name, password);
    users.put(user.id(), user);
    log.info("Сохранен пользователь: {}", user);
    return user;
  }

  public Optional<User> getById(UUID userId) {
    log.info("Поиск пользователя по ID: {}", userId);
    return Optional.ofNullable(users.get(userId));
  }

  public Optional<User> findByName(String name) {
    log.info("Поиск пользователя по имени: {}", name);
    return users.values().stream()
        .filter(user -> user.name().equals(name))
        .findFirst();
  }

  public boolean exists(String name) {
    log.info("Проверка существования пользователя с именем: {}", name);
    return users.values().stream().anyMatch(user -> user.name().equals(name));
  }

  public void delete(UUID userId) {
    users.remove(userId);
    log.info("Удален пользователь с ID: {}", userId);
  }
}
