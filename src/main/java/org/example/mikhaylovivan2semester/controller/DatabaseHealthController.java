package org.example.mikhaylovivan2semester.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/health")
public class DatabaseHealthController {

  private final DataSource dataSource;

  public DatabaseHealthController(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @GetMapping("/database")
  public ResponseEntity<String> checkDatabaseConnection() {
    try (Connection connection = dataSource.getConnection()) {
      if (connection.isValid(5)) {
        return ResponseEntity.ok("Database connection is healthy");
      } else {
        return ResponseEntity.status(503).body("Database connection is not valid");
      }
    } catch (SQLException e) {
      return ResponseEntity.status(503).body("Database connection failed: " + e.getMessage());
    }
  }
}
