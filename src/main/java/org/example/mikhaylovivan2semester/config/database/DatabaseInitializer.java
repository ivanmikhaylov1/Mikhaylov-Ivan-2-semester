package org.example.mikhaylovivan2semester.config.database;

import org.example.mikhaylovivan2semester.exception.DatabaseInitializationException;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DatabaseInitializer {
  public static void initializeTables(DataSource dataSource) {
    String sql = readSqlFile();
    try (Connection conn = dataSource.getConnection();
         Statement stmt = conn.createStatement()) {
      for (String query : sql.split(";")) {
        if (!query.trim().isEmpty()) {
          stmt.execute(query);
        }
      }
    } catch (SQLException e) {
      throw new DatabaseInitializationException("Не удалось выполнить SQL-запросы", e);
    }
  }

  private static String readSqlFile() {
    try (InputStream inputStream = DatabaseInitializer.class
        .getClassLoader()
        .getResourceAsStream("schema.sql")) {
      if (inputStream == null) {
        throw new DatabaseInitializationException("SQL файл 'schema.sql' не найден");
      }
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        return reader.lines().collect(Collectors.joining("\n"));
      }
    } catch (IOException e) {
      throw new DatabaseInitializationException("Ошибка при чтении SQL файла: schema.sql", e);
    }
  }
}
