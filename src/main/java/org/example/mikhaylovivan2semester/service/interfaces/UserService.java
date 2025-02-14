package org.example.mikhaylovivan2semester.service.interfaces;

import org.example.mikhaylovivan2semester.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
  List<UserDTO> findAll();
  Optional<UserDTO> save(String name, String password);
  Optional<UserDTO> getById(UUID userId);
  Optional<UserDTO> findByName(String name);
  boolean exists(String name);
}
