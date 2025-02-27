package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @NotBlank
  @Size(min = 3, max = 50)
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @NotBlank
  @Size(min = 6)
  @Column(name = "password", nullable = false)
  private String password;

  public User() {
    this.id = UUID.randomUUID();
  }

  public User(UUID id, String name, String password) {
    this.id = id != null ? id : UUID.randomUUID();
    this.name = name;
    this.password = password;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
