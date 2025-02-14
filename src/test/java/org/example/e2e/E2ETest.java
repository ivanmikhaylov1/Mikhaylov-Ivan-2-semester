package org.example.e2e;

import org.example.mikhaylovivan2semester.Application;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.entity.Article;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.entity.Website;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
class E2ETest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UserRepository userRepository;

  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.profiles.active", () -> "test");
  }

  @Test
  public void testFullFlow() {
    // 1. Создаем пользователя
    String username = "Test User";
    String password = "testpassword";

    // Отправляем запрос на создание пользователя
    UserDTO userDTO = createUser(username, password);
    assertNotNull(userDTO);
    assertNotNull(userDTO.id());
    assertEquals(username, userDTO.name());

    UUID userId = userDTO.id();

    // 2. Создаем статью и привязываем к пользователю
    Article article = new Article(UUID.randomUUID(),"Test Article", "Description of Test Article", "14.02.2025", "http://example.com");
    ResponseEntity<String> articleResponse = createArticle(article);
    assertEquals(HttpStatus.CREATED, articleResponse.getStatusCode());

    // 3. Создаем вебсайт и привязываем к пользователю
    Website website = new Website(UUID.randomUUID(), "Test Website", "https://www.test.com", userId);
    ResponseEntity<String> websiteResponse = createWebsite(website);
    assertEquals(HttpStatus.CREATED, websiteResponse.getStatusCode());

    // 4. Создаем каталог и привязываем к пользователю
    Catalog catalog = new Catalog(UUID.randomUUID(), "Test Catalog", userId);
    ResponseEntity<String> catalogResponse = createCatalog(catalog);
    assertEquals(HttpStatus.CREATED, catalogResponse.getStatusCode());

    // Проверяем все созданные данные
    assertUserExists(userId);
    assertArticleExists(userId);
    assertWebsiteExists(userId);
    assertCatalogExists(userId);
  }

  private UserDTO createUser(String username, String password) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // Используем DTO для создания пользователя
    UserDTO userDTO = new UserDTO(UUID.randomUUID(), username);

    HttpEntity<UserDTO> request = new HttpEntity<>(userDTO, headers);
    ResponseEntity<UserDTO> response = restTemplate.exchange("/users", HttpMethod.POST, request, UserDTO.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    return response.getBody();
  }

  private ResponseEntity<String> createArticle(Article article) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Article> request = new HttpEntity<>(article, headers);
    return restTemplate.exchange("/articles", HttpMethod.POST, request, String.class);
  }

  private ResponseEntity<String> createWebsite(Website website) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Website> request = new HttpEntity<>(website, headers);
    return restTemplate.exchange("/websites/user/{userId}", HttpMethod.POST, request, String.class, website.userId());
  }

  private ResponseEntity<String> createCatalog(Catalog catalog) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Catalog> request = new HttpEntity<>(catalog, headers);
    return restTemplate.exchange("/catalogs/user/{userId}", HttpMethod.POST, request, String.class, catalog.userId());
  }

  private void assertUserExists(UUID userId) {
    ResponseEntity<String> response = restTemplate.getForEntity("/users/{userId}", String.class, userId);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private void assertArticleExists(UUID userId) {
    ResponseEntity<String> response = restTemplate.getForEntity("/articles/user/{userId}", String.class, userId);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private void assertWebsiteExists(UUID userId) {
    ResponseEntity<String> response = restTemplate.getForEntity("/websites/user/{userId}", String.class, userId);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

  private void assertCatalogExists(UUID userId) {
    ResponseEntity<String> response = restTemplate.getForEntity("/catalogs/user/{userId}", String.class, userId);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }
}
