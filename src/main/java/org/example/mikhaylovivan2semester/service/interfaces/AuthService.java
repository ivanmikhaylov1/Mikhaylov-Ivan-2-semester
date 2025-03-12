package org.example.mikhaylovivan2semester.service.interfaces;

import jakarta.validation.constraints.NotBlank;
import org.example.mikhaylovivan2semester.dto.UserDTO;

public interface AuthService {
  UserDTO registerUser(String name, String password);

  String authenticate(@NotBlank String name, @NotBlank String password);

  String generateToken(String name);
}
