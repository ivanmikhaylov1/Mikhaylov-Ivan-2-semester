package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.repository.implementations.UserRepositoryImpl;
import org.example.mikhaylovivan2semester.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepositoryImpl userRepository;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserServiceImpl(UserRepositoryImpl userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  @Cacheable("users")
  public List<UserDTO> findAll() {
    return userRepository.findAll();
  }

  @Override
  @Cacheable(value = "usersByName", key = "#name")
  public Optional<UserDTO> findByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  @CacheEvict(value = "users", allEntries = true)
  public Optional<UserDTO> save(String name, String password) {
    String encryptedPassword = passwordEncoder.encode(password);
    return Optional.ofNullable(userRepository.save(name, encryptedPassword));
  }

  @Override
  @CacheEvict(value = "users", allEntries = true)
  public Optional<UserDTO> getById(UUID userId) {
    return userRepository.getById(userId);
  }

  @Override
  @CacheEvict(value = "usersByName", key = "#name")
  public boolean exists(String name) {
    return userRepository.exists(name);
  }
}
