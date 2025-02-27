package org.example.mikhaylovivan2semester.repository;

import jakarta.transaction.Transactional;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, UUID> {
    @Query("SELECT c FROM Catalog c WHERE (c.userId = ?1 OR c.userId IS NULL) AND c.name = ?2")
    Optional<Catalog> findByUserIdAndName(UUID userId, String name);

    void deleteByUserIdAndName(UUID userId, String name);

    boolean existsByName(String name);

    @Query("SELECT c FROM Catalog c WHERE c.userId IS NULL")
    List<Catalog> findBasicCatalogs();

    @Query("SELECT c FROM Catalog c WHERE c.userId = ?1")
    List<Catalog> findByUserId(UUID userId);

    @Modifying
    @Transactional
    @Query("INSERT INTO Catalog (id, name, userId) VALUES (?1, ?2, ?3)")
    Catalog saveToUser(UUID id, String name, UUID userId);
}
