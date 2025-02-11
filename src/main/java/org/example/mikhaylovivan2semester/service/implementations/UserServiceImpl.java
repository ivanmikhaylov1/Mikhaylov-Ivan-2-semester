package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.example.mikhaylovivan2semester.service.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  private UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> save(String name, String password) {
    return Optional.ofNullable(userRepository.save(name, password));
  }

  @Override
  public Optional<User> getById(UUID userId) {
    return userRepository.getById(userId);
  }

  @Override
  public Optional<User> findByName(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public boolean exists(String name) {
    return userRepository.exists(name);
  }
}
