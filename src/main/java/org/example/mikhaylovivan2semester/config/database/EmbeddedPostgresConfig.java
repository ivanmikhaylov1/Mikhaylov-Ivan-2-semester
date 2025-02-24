package org.example.mikhaylovivan2semester.config.database;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class EmbeddedPostgresConfig {
  private static final int FIXED_PORT = 5432;
  private EmbeddedPostgres embeddedPostgres;

  @Bean
  public DataSource dataSource() throws IOException, SQLException {
    embeddedPostgres = EmbeddedPostgres.builder()
        .setPort(FIXED_PORT)
        .start();

    DataSource defaultDs = embeddedPostgres.getPostgresDatabase();

    try (Connection conn = defaultDs.getConnection();
         Statement stmt = conn.createStatement()) {
      stmt.execute("CREATE ROLE postgres WITH LOGIN SUPERUSER PASSWORD 'postgres'");
    } catch (SQLException e) {
      if (!e.getMessage().toLowerCase().contains("already exists")) {
        throw e;
      }
    }

    try (Connection conn = defaultDs.getConnection("postgres", "postgres");
         Statement stmt = conn.createStatement()) {
      try {
        stmt.execute("CREATE DATABASE mydb");
      } catch (SQLException e) {
        if (!e.getMessage().toLowerCase().contains("already exists")) {
          throw e;
        }
      }
    }

    org.postgresql.ds.PGSimpleDataSource pgDataSource = new org.postgresql.ds.PGSimpleDataSource();
    pgDataSource.setURL(String.format(
        "jdbc:postgresql://localhost:%d/mydb",
        FIXED_PORT
    ));
    pgDataSource.setUser("postgres");
    pgDataSource.setPassword("postgres");
    DatabaseInitializer.initializeTables(pgDataSource);
    return pgDataSource;
  }

  @PreDestroy
  public void stopEmbeddedPostgres() throws IOException {
    if (embeddedPostgres != null) {
      embeddedPostgres.close();
    }
  }
}
