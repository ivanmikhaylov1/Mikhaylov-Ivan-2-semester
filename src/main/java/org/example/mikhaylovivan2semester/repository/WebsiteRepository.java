package org.example.mikhaylovivan2semester.repository;

import jakarta.transaction.Transactional;
import org.example.mikhaylovivan2semester.entity.User;
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
  @Query("SELECT w FROM Website w WHERE w.user IS NULL")
  List<Website> findBasicWebsites();

  List<Website> findByUser(User user);

  boolean existsByName(String name);

  Optional<Website> findByUserAndName(User user, String name);

  @Modifying
  @Transactional
  void deleteByUserAndName(User user, String name);

  default Website addUserWebsite(User user, String name, String url) {
    Website website = new Website(name, url, user);
    return save(website);
  }
}
