package org.example.config;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class EmbeddedPostgresConfig {

  @Bean(destroyMethod = "close")
  public EmbeddedPostgres embeddedPostgres() throws IOException {
    return EmbeddedPostgres.builder()
        .start();
  }

  @Bean
  @Primary
  public DataSource dataSource(EmbeddedPostgres embeddedPostgres) {
    return embeddedPostgres.getPostgresDatabase();
  }
}
