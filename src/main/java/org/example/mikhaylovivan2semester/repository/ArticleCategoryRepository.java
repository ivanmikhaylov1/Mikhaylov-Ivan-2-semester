package org.example.mikhaylovivan2semester.repository;

import org.example.mikhaylovivan2semester.entity.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArticleCategoryRepository extends JpaRepository<ArticleCategory, UUID> {
  Optional<ArticleCategory> findByName(String name);
} 