package org.example.mikhaylovivan2semester.repository;

import org.example.config.TestRepoConfig;
import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.entity.ArticleCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestRepoConfig.class)
class ArticleRepositoryTest {

  @Autowired
  private ArticleRepository articleRepository;

  @Autowired
  private ArticleCategoryRepository categoryRepository;

  @Test
  void shouldSaveAndFindArticle() {
    ArticleCategory category = new ArticleCategory();
    category.setId(UUID.randomUUID());
    category.setName("Test Category");
    categoryRepository.save(category);
    Article article = new Article();
    article.setId(UUID.randomUUID());
    article.setTitle("Test Article");
    article.setContent("Test Content");
    article.setDate("2024-03-20");
    article.setLink("http://test.com");
    article.setCreatedAt(LocalDateTime.now());
    article.setCategory(category);

    assertThat(articleRepository.findById(article.getId())).isPresent()
        .hasValueSatisfying(found -> {
          assertThat(found.getTitle()).isEqualTo("Test Article");
          assertThat(found.getCategory().getId()).isEqualTo(category.getId());
        });
  }
} 