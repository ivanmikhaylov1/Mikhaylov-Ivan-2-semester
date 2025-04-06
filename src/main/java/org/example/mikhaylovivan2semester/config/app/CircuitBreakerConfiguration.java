package org.example.mikhaylovivan2semester.config.app;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfiguration {

  @Bean
  public CircuitBreakerRegistry circuitBreakerRegistry() {
    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
        .failureRateThreshold(50)
        .waitDurationInOpenState(Duration.ofMillis(1000))
        .slidingWindowSize(2)
        .build();

    return CircuitBreakerRegistry.of(config);
  }

  @Bean
  public CircuitBreaker catalogServiceCircuitBreaker(CircuitBreakerRegistry registry) {
    return registry.circuitBreaker("catalog-service");
  }

  @Bean
  public CircuitBreaker websiteServiceCircuitBreaker(CircuitBreakerRegistry registry) {
    return registry.circuitBreaker("website-service");
  }
}