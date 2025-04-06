package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @NotBlank
  @Size(min = 3, max = 50)
  @Column(name = "name", nullable = false, length = 50, unique = true)
  private String name;

  @NotBlank
  @Size(min = 6)
  @Column(name = "password", nullable = false)
  private String password;

  public User(String name, String password) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.password = password;
  }
}
