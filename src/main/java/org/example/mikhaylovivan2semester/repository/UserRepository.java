package org.example.mikhaylovivan2semester.repository;

import lombok.extern.slf4j.Slf4j;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.entity.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class UserRepository {
  private static final Map<UUID, User> users = new HashMap<>();

  public List<UserDTO> findAll() {
    log.info("Получены все пользователи");
    List<UserDTO> userDTOList = new ArrayList<>();
    for (User user : users.values()) {
      userDTOList.add(new UserDTO(user.id(), user.name()));
    }
    return userDTOList;
  }

  public UserDTO save(String name, String password) {
    User user = new User(UUID.randomUUID(), name, password);
    users.put(user.id(), user);
    log.info("Сохранен пользователь с именем: {}", user.name());
    return new UserDTO(user.id(), user.name());
  }

  public Optional<UserDTO> getById(UUID userId) {
    log.info("Поиск пользователя по ID: {}", userId);
    User user = users.get(userId);
    if (user != null) {
      return Optional.of(new UserDTO(user.id(), user.name()));
    } else {
      return Optional.empty();
    }
  }

  public Optional<UserDTO> findByName(String name) {
    log.info("Поиск пользователя по имени: {}", name);
    return users.values().stream()
        .filter(user -> user.name().equals(name))
        .findFirst()
        .map(user -> new UserDTO(user.id(), user.name()));
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
