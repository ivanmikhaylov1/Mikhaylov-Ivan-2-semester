package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {
  @Id
  @Column(name = "article_id", updatable = false, nullable = false)
  private UUID id;

  @NotBlank
  @Size(min = 3, max = 255)
  @Column(name = "title", nullable = false, length = 255)
  private String title;

  @NotBlank
  @Size(min = 10, max = 500)
  @Column(name = "content", nullable = false, length = 500)
  private String description;

  @NotBlank
  @Column(name = "date", nullable = false)
  private String date;

  @NotBlank
  @Column(name = "link", nullable = false)
  private String link;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private ArticleCategory category;

  public Article(String title, String description, String date, String link) {
    this.id = UUID.randomUUID();
    this.title = title;
    this.description = description;
    this.date = date;
    this.link = link;
    this.createdAt = LocalDateTime.now();
  }
}
