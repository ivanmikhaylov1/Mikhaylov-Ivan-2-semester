package org.example.mikhaylovivan2semester.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.mikhaylovivan2semester.entity.ArticleCategory;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCategoryDTO {
  private UUID id;
  private String name;

  public ArticleCategoryDTO(ArticleCategory category) {
    this.id = category.getId();
    this.name = category.getName();
  }
} 