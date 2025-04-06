package org.example.mikhaylovivan2semester;

import org.example.config.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {Application.class, TestConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
class MikhaylovIvan2SemesterApplicationTests {

  @Test
  void contextLoads() {}

} 