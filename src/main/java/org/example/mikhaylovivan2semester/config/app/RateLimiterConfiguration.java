package org.example.mikhaylovivan2semester.config.app;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimiterConfiguration {

  @Bean
  public RateLimiterRegistry rateLimiterRegistry() {
    RateLimiterConfig config = RateLimiterConfig.custom()
        .limitForPeriod(10)
        .limitRefreshPeriod(Duration.ofSeconds(1))
        .timeoutDuration(Duration.ofMillis(500))
        .build();

    return RateLimiterRegistry.of(config);
  }

  @Bean
  public RateLimiter userServiceRateLimiter(RateLimiterRegistry registry) {
    return registry.rateLimiter("user-service");
  }

  @Bean
  public RateLimiter websiteServiceRateLimiter(RateLimiterRegistry registry) {
    return registry.rateLimiter("website-service");
  }
}