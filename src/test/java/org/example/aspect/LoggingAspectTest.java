package org.example.aspect;

import org.example.mikhaylovivan2semester.Application;
import org.example.mikhaylovivan2semester.aspect.LoggingAspect;
import org.example.mikhaylovivan2semester.service.implementations.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
class LoggingAspectTest {
  @Autowired
  private LoggingAspect loggingAspect;

  @Autowired
  private UserServiceImpl userService;

  @Test
  void testAspectCounter() {
    int initialCount = loggingAspect.getCounter();
    userService.getRandomUserFromExternalAPI();
    assertThat(loggingAspect.getCounter()).isEqualTo(initialCount + 2);
  }
}
