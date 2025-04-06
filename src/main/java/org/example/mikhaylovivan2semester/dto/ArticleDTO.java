package org.example.mikhaylovivan2semester.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.mikhaylovivan2semester.entity.Article;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
  private UUID id;
  private String title;
  private String content;
  private String date;
  private String link;
  private LocalDateTime createdAt;
  private UUID categoryId;
  private String categoryName;

  public ArticleDTO(Article article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.content = article.getContent();
    this.date = article.getDate();
    this.link = article.getLink();
    this.createdAt = article.getCreatedAt();
    if (article.getCategory() != null) {
      this.categoryId = article.getCategory().getId();
      this.categoryName = article.getCategory().getName();
    }
  }
} 