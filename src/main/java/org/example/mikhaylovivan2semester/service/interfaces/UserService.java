package org.example.mikhaylovivan2semester.service.interfaces;

import org.example.mikhaylovivan2semester.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
  List<User> findAll();
  Optional<User> save(String name, String password);
  Optional<User> getById(UUID userId);
  Optional<User> findByName(String name);
  boolean exists(String name);
}
