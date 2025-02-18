package org.example.mikhaylovivan2semester.repository.interfaces;

import org.example.mikhaylovivan2semester.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  List<UserDTO> findAll();

  UserDTO save(String name, String password);

  Optional<UserDTO> getById(UUID userId);

  Optional<UserDTO> findByName(String name);

  boolean exists(String name);

  void delete(UUID userId);
}
