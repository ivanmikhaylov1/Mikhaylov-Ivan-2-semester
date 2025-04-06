package org.example.mikhaylovivan2semester.repository;

import org.example.config.TestRepoConfig;
import org.example.mikhaylovivan2semester.entity.ArticleCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = TestRepoConfig.class)
class ArticleCategoryRepositoryTest {

  @Autowired
  private ArticleCategoryRepository categoryRepository;

  @Test
  void shouldSaveAndFindCategory() {
    ArticleCategory category = new ArticleCategory();
    category.setId(UUID.randomUUID());
    category.setName("Test Category");
    ArticleCategory savedCategory = categoryRepository.save(category);

    assertThat(savedCategory.getId()).isNotNull();
    assertThat(savedCategory.getName()).isEqualTo("Test Category");
    assertThat(categoryRepository.findByName("Test Category")).isPresent();
  }
} 