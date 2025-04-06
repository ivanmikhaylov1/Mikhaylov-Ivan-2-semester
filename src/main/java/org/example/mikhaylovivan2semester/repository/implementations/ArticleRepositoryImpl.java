package org.example.mikhaylovivan2semester.repository.implementations;

import org.example.mikhaylovivan2semester.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;
import java.util.List;

@Repository
public class ArticleRepositoryImpl {
  private final RestTemplate restTemplate;
  private final WebClient webClient;

  @Autowired
  public ArticleRepositoryImpl(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
    this.restTemplate = restTemplate;
    this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
  }

  public List<Article> findAll() {
    String randomApiUrl = "https://jsonplaceholder.typicode.com/posts/1";
    String response = restTemplate.getForObject(randomApiUrl, String.class);
    System.out.println("Рандомный API запрос: " + response);
    return Collections.emptyList();
  }

  public String getRandomPostBlocking() {
    int randomId = (int) (Math.random() * 100) + 1;
    return webClient.get()
        .uri("/posts/" + randomId)
        .retrieve()
        .bodyToMono(String.class)
        .doOnError(error -> System.err.println("Ошибка WebClient: " + error.getMessage()))
        .block();
  }
}
