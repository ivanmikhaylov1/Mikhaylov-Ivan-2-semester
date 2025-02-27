package org.example.mikhaylovivan2semester.repository;

import jakarta.transaction.Transactional;
import org.example.mikhaylovivan2semester.entity.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, UUID> {
    @Query("SELECT w FROM Website w WHERE w.userId IS NULL")
    List<Website> findBasicWebsites();
    
    @Query("SELECT w FROM Website w WHERE w.userId = ?1")
    List<Website> findByUserId(UUID userId);
    
    boolean existsByName(String name);
    
    @Query("SELECT w FROM Website w WHERE (w.userId = ?1 OR w.userId IS NULL) AND w.name = ?2")
    Optional<Website> findByUserIdAndName(UUID userId, String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Website w WHERE w.userId = ?1 AND w.name = ?2")
    void deleteByUserIdAndName(UUID userId, String name);
    
    default Website addUserWebsite(UUID userId, String name, String url) {
        Website website = new Website(UUID.randomUUID(), name, url, userId);
        return save(website);
    }
}
