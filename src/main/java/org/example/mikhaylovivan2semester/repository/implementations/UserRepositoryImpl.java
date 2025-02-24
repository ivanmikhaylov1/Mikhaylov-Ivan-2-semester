package org.example.mikhaylovivan2semester.repository.implementations;

import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.repository.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
  private static final Map<UUID, User> users = new HashMap<>();

  @Override
  public List<UserDTO> findAll() {
    List<UserDTO> userDTOList = new ArrayList<>();
    for (User user : users.values()) {
      userDTOList.add(new UserDTO(user.id(), user.name()));
    }
    return userDTOList;
  }

  @Override
  public UserDTO save(String name, String password) {
    User user = new User(UUID.randomUUID(), name, password);
    users.put(user.id(), user);
    return new UserDTO(user.id(), user.name());
  }

  @Override
  public Optional<UserDTO> getById(UUID userId) {
    User user = users.get(userId);
    if (user != null) {
      return Optional.of(new UserDTO(user.id(), user.name()));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Optional<UserDTO> findByName(String name) {
    return users.values().stream()
        .filter(user -> Objects.equals(user.name(), name))
        .findFirst()
        .map(user -> new UserDTO(user.id(), user.name()));
  }

  @Override
  public boolean exists(String name) {
    return users.values().stream().anyMatch(user -> Objects.equals(user.name(), name));
  }

  @Override
  public void delete(UUID userId) {
    users.remove(userId);
  }

  @Override
  public Optional<User> findEntityByName(String name) {
    return users.values().stream()
        .filter(user -> Objects.equals(user.name(), name))
        .findFirst();
  }
}
