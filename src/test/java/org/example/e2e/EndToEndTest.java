package org.example.e2e;

import org.example.config.TestConfig;
import org.example.mikhaylovivan2semester.Application;
import org.example.mikhaylovivan2semester.dto.response.Response;
import org.example.mikhaylovivan2semester.dto.UserDTO;
import org.example.mikhaylovivan2semester.dto.request.create.CreateUserRequest;
import org.example.mikhaylovivan2semester.entity.User;
import org.example.mikhaylovivan2semester.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {Application.class, TestConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
class EndToEndTest {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TestRestTemplate restTemplate;
  @LocalServerPort
  private int port;
  private String baseUrl;
  private UUID userId;
  private String testUserName;

  @BeforeAll
  void setUp() {
    baseUrl = "http://localhost:" + port;
    createTestUser();
  }

  private void createTestUser() {
    testUserName = "Test User 10" + UUID.randomUUID();
    User user = new User(UUID.randomUUID(), testUserName, "testpassword");
    User savedUser = userRepository.save(user);
    userId = savedUser.getId();
  }

  @Test
  void testSaveUser() {
    String uniqueName = "Test User " + UUID.randomUUID();
    CreateUserRequest requestBody = new CreateUserRequest(uniqueName, "testpassword");
    ResponseEntity<Response<UserDTO>> response = restTemplate.exchange(
        baseUrl + "/users",
        HttpMethod.POST,
        new HttpEntity<>(requestBody),
        new ParameterizedTypeReference<Response<UserDTO>>() {
        }
    );

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data()).isNotNull();
    assertThat(response.getBody().data().name()).isEqualTo(uniqueName);
  }

  @Test
  void testGetAllUsers() {
    ResponseEntity<Response<List<UserDTO>>> response = restTemplate.exchange(
        baseUrl + "/users",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<>() {
        }
    );
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data()).isNotNull();
    assertThat(response.getBody().data()).isNotEmpty();
  }

  @Test
  void testGetUserById() {
    ResponseEntity<Response<UserDTO>> response = restTemplate.exchange(
        baseUrl + "/users/" + userId,
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<Response<UserDTO>>() {
        }
    );
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data()).isNotNull();
  }

  @Test
  void testGetUserByName() {
    ResponseEntity<Response<UserDTO>> response = restTemplate.exchange(
        baseUrl + "/users/by-name?name=" + URLEncoder.encode(testUserName, StandardCharsets.UTF_8),
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<Response<UserDTO>>() {
        }
    );
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
    assertThat(response.getBody().data()).isNotNull();
    assertThat(response.getBody().data().name()).isEqualTo(testUserName);
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
}
