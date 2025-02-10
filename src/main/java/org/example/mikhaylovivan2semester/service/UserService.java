package org.example.mikhaylovivan2semester.service;

import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService {
  private final UserRepository userRepository = new UserRepository();

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> save(String name, String password) {
    return Optional.of(userRepository.save(name, password));
  }

  public Optional<Optional<User>> getById(UUID userId) {
    return Optional.ofNullable(userRepository.getById(userId));
  }

  public Optional<Optional<User>> findByName(String name) {
    return Optional.ofNullable(userRepository.findByName(name));
  }

  public boolean exists(String name) {
    return userRepository.exists(name);
  }
}
