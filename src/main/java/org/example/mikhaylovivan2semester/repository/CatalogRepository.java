package org.example.mikhaylovivan2semester.repository;

import jakarta.transaction.Transactional;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, UUID> {
  Optional<Catalog> findByUserAndName(User user, String name);

  void deleteByUserAndName(User user, String name);

  boolean existsByName(String name);

  @Query("SELECT c FROM Catalog c WHERE c.user IS NULL")
  List<Catalog> findBasicCatalogs();

  List<Catalog> findByUser(User user);

  @Modifying
  @Transactional
  @Query("UPDATE Catalog c SET c.name = ?2 WHERE c.catalogId = ?1")
  void updateName(UUID id, String name);
}
