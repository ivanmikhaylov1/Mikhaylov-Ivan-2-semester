package org.example.mikhaylovivan2semester.config.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableRetry
public class RetryConfig {

  @Bean
  public RetryTemplate retryTemplate() {
    RetryTemplate retryTemplate = new RetryTemplate();

    ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
    backOffPolicy.setInitialInterval(1000L);
    backOffPolicy.setMaxInterval(10000L);
    backOffPolicy.setMultiplier(2.0);

    Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
    retryableExceptions.put(Exception.class, true);

    SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(3, retryableExceptions);

    retryTemplate.setBackOffPolicy(backOffPolicy);
    retryTemplate.setRetryPolicy(retryPolicy);

    return retryTemplate;
  }
}