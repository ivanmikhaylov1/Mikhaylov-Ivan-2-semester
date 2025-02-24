package org.example.mikhaylovivan2semester.config;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.security.Key;

@Getter
@Component
public class JwtKeyProvider {
  private final Key key;

  public JwtKeyProvider() {
    this.key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
  }
}
