//package org.example.e2e;
//
//import org.example.mikhaylovivan2semester.Application;
//import org.example.mikhaylovivan2semester.dto.UserDTO;
//import org.example.mikhaylovivan2semester.entity.User;
//import org.example.mikhaylovivan2semester.repository.UserRepository;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.annotation.DirtiesContext;
//
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class, properties = "spring.config.location=classpath:/application-test.yml")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
//class EndToEndTest {
//  @Autowired
//  private UserRepository userRepository;
//
//  @Autowired
//  private TestRestTemplate restTemplate;
//
//  @LocalServerPort
//  private int port;
//
//  private String baseUrl;
//  private UUID userId;
//
//  @BeforeAll
//  void setUp() {
//    baseUrl = "http://localhost:" + port;
//    createTestUser();
//  }
//
//  private void createTestUser() {
//    User user = new User(null, "Test User", "testpassword");
//    User savedUser = userRepository.save(user);
//    userId = savedUser.getId();
//  }
//
//  @Test
//  void testSaveUser() {
//    Map<String, String> requestBody = new HashMap<>();
//    requestBody.put("name", "Test User");
//    requestBody.put("password", "testpassword");
//
//    ResponseEntity<String> response = restTemplate.postForEntity(
//        baseUrl + "/users",
//        requestBody,
//        String.class
//    );
//
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//  }
//
//  @Test
//  void testGetAllUsers() {
//    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/users", String.class);
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//  }
//
//  @Test
//  void testGetUserById() {
//    ResponseEntity<UserDTO> response = restTemplate.getForEntity(baseUrl + "/users/" + userId, UserDTO.class);
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//  }
//
//  @Test
//  void testGetUserByName() {
//    ResponseEntity<String> response = restTemplate.getForEntity(
//        baseUrl + "/users/by-name?name=" + URLEncoder.encode("Test User", StandardCharsets.UTF_8),
//        String.class
//    );
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//  }
//
//  @Test
//  void testCheckIfUserExists() {
//    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/users/exists?name=Test%20User", String.class);
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//  }
//
//  @Test
//  void testGetArticles() {
//    ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/articles", String.class);
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//  }
//}
