package org.example.mikhaylovivan2semester.service.implementations;

import io.jsonwebtoken.Jwts;
import jakarta.validation.constraints.NotBlank;
import org.example.mikhaylovivan2semester.config.security.JwtKeyProvider;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.exception.InvalidCredentialsException;
import org.example.mikhaylovivan2semester.exception.UserAlreadyExistsException;
import org.example.mikhaylovivan2semester.repository.interfaces.UserRepository;
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
    if (userRepository.exists(name)) {
      throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
    }
    String hashedPassword = passwordEncoder.encode(password);
    return userRepository.save(name, hashedPassword);
  }

  @Override
  public String authenticate(@NotBlank String name, @NotBlank String password) {
    Optional<User> userOptional = userRepository.findEntityByName(name);
    if (userOptional.isEmpty()) {
      throw new InvalidCredentialsException("Неверное имя пользователя или пароль");
    }
    User user = userOptional.get();
    if (!passwordEncoder.matches(password, user.password())) {
      throw new InvalidCredentialsException("Неверное имя пользователя или пароль");
    }
    return generateToken(name);
  }

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
