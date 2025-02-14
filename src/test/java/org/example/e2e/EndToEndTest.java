package org.example.e2e;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.mikhaylovivan2semester.Application;
import org.example.mikhaylovivan2semester.entity.response.CreateArticleRequest;
import org.example.mikhaylovivan2semester.entity.response.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class EndToEndTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  private UUID userId;
  private UUID articleId;

  @BeforeEach
  void setup() {
    userId = createUser("testUser", "password123");
    articleId = createArticle("Test Article", "This is a test description", "2025-02-14", "http://example.com");
  }

  private UUID createUser(String name, String password) {
    CreateUserRequest createUserRequest = new CreateUserRequest(name, password);
    Request<CreateUserRequest> request = new Request<>(createUserRequest);

    ResponseEntity<String> response = restTemplate.postForEntity("/users", request, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    return extractId(response.getBody());
  }

  private UUID createArticle(String name, String description, String date, String link) {
    CreateArticleRequest createArticleRequest = new CreateArticleRequest(name, description, date, link);
    Request<CreateArticleRequest> request = new Request<>(createArticleRequest);

    ResponseEntity<String> response = restTemplate.postForEntity("/articles", request, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    return extractId(response.getBody());
  }

  private UUID extractId(String json) {
    try {
      JsonNode node = objectMapper.readTree(json);
      return UUID.fromString(node.get("data").get("id").asText());
    } catch (Exception e) {
      throw new RuntimeException("Ошибка при парсинге ID из JSON: " + json);
    }
  }

  @Test
  void testFindAllUsers() {
    ResponseEntity<String> response = restTemplate.getForEntity("/users", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("testUser");
  }

  @Test
  void testGetUserById() {
    ResponseEntity<String> response = restTemplate.getForEntity("/users/" + userId, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("testUser");
  }

  @Test
  void testFindUserByName() {
    ResponseEntity<String> response = restTemplate.getForEntity("/users/by-name?name=testUser", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("testUser");
  }

  @Test
  void testUserExists() {
    ResponseEntity<String> response = restTemplate.getForEntity("/users/exists?name=testUser", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("true");
  }

  @Test
  void testFindAllArticles() {
    ResponseEntity<String> response = restTemplate.getForEntity("/articles", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("Test Article");
  }

  @Test
  void testGetArticleById() {
    ResponseEntity<String> response = restTemplate.getForEntity("/articles/" + articleId, String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).contains("Test Article");
  }
}
