package org.example.mikhaylovivan2semester.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Endpoint(id = "random-uuid")
public class RandomUuidEndpoint {

  @ReadOperation
  public String getRandomUuid() {
    return UUID.randomUUID().toString();
  }
}
