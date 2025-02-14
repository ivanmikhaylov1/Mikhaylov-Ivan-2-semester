package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.example.mikhaylovivan2semester.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  private UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public List<UserDTO> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<UserDTO> save(String name, String password) {
    String encryptedPassword = passwordEncoder.encode(password);
    return Optional.ofNullable(userRepository.save(name, encryptedPassword));
  }

  @Override
  public Optional<UserDTO> getById(UUID userId) {
    return userRepository.getById(userId);
  }

  @Override
  public Optional<UserDTO> findByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public boolean exists(String name) {
    return userRepository.exists(name);
  }
}
