package org.example.mikhaylovivan2semester.service.implementations;

import io.jsonwebtoken.Jwts;
import jakarta.validation.constraints.NotBlank;
import org.example.mikhaylovivan2semester.config.security.JwtKeyProvider;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.exception.InvalidCredentialsException;
import org.example.mikhaylovivan2semester.exception.UserAlreadyExistsException;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.example.mikhaylovivan2semester.service.interfaces.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;
  private final Key key;

  public AuthServiceImpl(UserRepository userRepository, JwtKeyProvider jwtKeyProvider) {
    this.userRepository = userRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
    this.key = jwtKeyProvider.getKey();
  }

  @Override
  public UserDTO registerUser(String name, String password) {
    if (userRepository.existsByName(name)) {
      throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
    }
    String hashedPassword = passwordEncoder.encode(password);
    User user = new User(null, name, hashedPassword);
    User savedUser = userRepository.save(user);
    return new UserDTO(savedUser.getId(), savedUser.getName());
  }

  @Override
  public String authenticate(@NotBlank String name, @NotBlank String password) {
    Optional<User> userOptional = userRepository.findByName(name);
    if (userOptional.isEmpty()) {
      throw new InvalidCredentialsException("Неверное имя пользователя или пароль");
    }
    User user = userOptional.get();
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new InvalidCredentialsException("Неверное имя пользователя или пароль");
    }
    return generateToken(name);
  }

  @SuppressWarnings("deprecation")
  @Override
  public String generateToken(String name) {
    long now = System.currentTimeMillis();
    return Jwts.builder()
        .setSubject(name)
        .setIssuedAt(new Date(now))
        .setExpiration(new Date(now + 3600 * 1000))
        .signWith(key)
        .compact();
  }
}
