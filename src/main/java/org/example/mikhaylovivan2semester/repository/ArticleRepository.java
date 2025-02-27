package org.example.mikhaylovivan2semester.repository;

import org.example.mikhaylovivan2semester.entity.Article;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {
    @NotNull List<Article> findAll();

    @Modifying
    @Transactional
    @Query(value = "UPDATE user_last_request SET last_request_time = ?2 WHERE user_id = ?1", nativeQuery = true)
    void updateUserLastRequestTime(UUID userId, Timestamp timestamp);

    @Query(value = "SELECT last_request_time FROM user_last_request WHERE user_id = ?1", nativeQuery = true)
    Timestamp getUserLastRequestTime(UUID userId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO article_categories (article_id, catalog_id, website_id) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void saveArticleCategory(UUID articleId, UUID catalogId, UUID websiteId);
}
