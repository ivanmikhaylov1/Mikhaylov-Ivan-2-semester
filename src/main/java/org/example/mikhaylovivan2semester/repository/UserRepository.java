package org.example.mikhaylovivan2semester.repository;

import org.example.mikhaylovivan2semester.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByName(String name);
  boolean existsByName(String name);
}
