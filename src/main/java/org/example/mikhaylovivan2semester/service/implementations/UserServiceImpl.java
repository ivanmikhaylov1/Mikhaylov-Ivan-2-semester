package org.example.mikhaylovivan2semester.service.implementations;

import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.example.mikhaylovivan2semester.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final RestTemplate restTemplate;
  private final Random random = new Random();

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
                         PasswordEncoder passwordEncoder,
                         RestTemplate restTemplate) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.restTemplate = restTemplate;
  }

  public void getRandomUserFromExternalAPI() {
    int randomId = random.nextInt() * 100;
    String url = "https://jsonplaceholder.typicode.com/users/" + randomId;

    try {
      restTemplate.getForObject(url, String.class);
    } catch (Exception e) {
      System.err.println("Ошибка при вызове внешнего API: " + e.getMessage());
    }
  }

  @Override
  @Cacheable("users")
  public List<UserDTO> findAll() {
    return userRepository.findAll().stream()
        .map(user -> new UserDTO(user.getId(), user.getName()))
        .collect(Collectors.toList());
  }

  @Override
  @Cacheable(value = "usersByName", key = "#name")
  public Optional<UserDTO> findByName(String name) {
    return userRepository.findByName(name)
        .map(user -> new UserDTO(user.getId(), user.getName()));
  }

  @Override
  @CacheEvict(value = "users", allEntries = true)
  public Optional<UserDTO> save(String name, String password) {
    if (exists(name)) {
      return Optional.empty();
    }
    String encryptedPassword = passwordEncoder.encode(password);
    User user = new User(null, name, encryptedPassword);
    User savedUser = userRepository.save(user);
    return Optional.of(new UserDTO(savedUser.getId(), savedUser.getName()));
  }

  @Override
  @CacheEvict(value = "users", allEntries = true)
  public Optional<UserDTO> getById(UUID userId) {
    return userRepository.findById(userId)
        .map(user -> new UserDTO(user.getId(), user.getName()));
  }

  @Override
  @CacheEvict(value = "usersByName", key = "#name")
  public boolean exists(String name) {
    return userRepository.existsByName(name);
  }
}
