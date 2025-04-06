package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "catalogs")
@Getter
@Setter
@NoArgsConstructor
public class Catalog {

  @Id
  @Column(name = "catalog_id", updatable = false, nullable = false)
  private UUID catalogId;

  @NotBlank
  @Size(min = 3, max = 20)
  @Column(name = "name", nullable = false, length = 20)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Catalog(String name, User user) {
    this.catalogId = UUID.randomUUID();
    this.name = name;
    this.user = user;
  }
}

