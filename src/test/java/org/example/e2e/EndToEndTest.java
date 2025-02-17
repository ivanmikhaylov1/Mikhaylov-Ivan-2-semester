package org.example.e2e;

import org.example.mikhaylovivan2semester.Application;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class, properties = "spring.config.location=classpath:/application-test.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class EndToEndTest {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TestRestTemplate restTemplate;

  @LocalServerPort
  private int port;

  private String baseUrl;
  private UUID userId;

  @BeforeAll
  void setUp() {
    baseUrl = "http://localhost:" + port;
    createTestUser();
  }

  private void createTestUser() {
    UserDTO testUser = userRepository.save("Test User", "testpassword");
    userId = testUser.id();
  }

  @Test
  void testSaveUser() {
    Map<String, String> requestBody = new HashMap<>();
    requestBody.put("name", "Test User");
    requestBody.put("password", "testpassword");

    ResponseEntity<String> response = restTemplate.postForEntity(
        baseUrl + "/users",
        requestBody,
        String.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
  }

  @Test
  void testGetAllUsers() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/users", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetUserById() {
    ResponseEntity<UserDTO> response = restTemplate.getForEntity(baseUrl + "/users/" + userId, UserDTO.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetUserByName() {
    ResponseEntity<String> response = restTemplate.getForEntity(
        baseUrl + "/users/by-name?name=" + URLEncoder.encode("Test User", StandardCharsets.UTF_8),
        String.class
    );
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testCheckIfUserExists() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/users/exists?name=Test%20User", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetArticles() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/articles", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testPutUserLastRequestTime() {
    ResponseEntity<String> response = restTemplate.exchange(
        baseUrl + "/articles/user/" + userId + "/lastRequestTime",
        HttpMethod.PUT,
        null,
        String.class
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetUserLastRequestTime() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/articles/user/" + userId + "/lastRequestTime", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetBasicWebsites() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/websites/basic", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetUserWebsites() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/websites/user/8b9082bf-36ab-4aff-870f-3cbe13573d7f", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testCheckIfWebsiteExists() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/websites/exists?name=Test%20Website", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetBasicCatalogs() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/catalogs/basic", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetUserCatalogs() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/catalogs/user/8b9082bf-36ab-4aff-870f-3cbe13573d7f", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testCheckIfCatalogExists() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/catalogs/exists?name=Test%20Catalog", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }

  @Test
  void testGetCatalogByName() {
    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/catalogs/name?userId=8b9082bf-36ab-4aff-870f-3cbe13573d7f&name=Test%20Catalog", String.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
}
