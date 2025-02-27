package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "article_categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategory {
  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @NotBlank
  @Size(min = 3, max = 50)
  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Article> articles;

  public ArticleCategory(UUID id, String name) {
    this.id = id != null ? id : UUID.randomUUID();
    this.name = name;
  }
}