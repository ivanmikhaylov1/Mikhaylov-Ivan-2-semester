package org.example.mikhaylovivan2semester.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager manager = new CaffeineCacheManager("articles", "users", "usersByName");
    manager.setCaffeine(caffeineConfig());
    return manager;
  }

  private Caffeine<Object, Object> caffeineConfig() {
    return Caffeine.newBuilder()
        .maximumSize(100)
        .expireAfterWrite(10, TimeUnit.MINUTES);
  }
}
