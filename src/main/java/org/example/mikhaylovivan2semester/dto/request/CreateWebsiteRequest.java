package org.example.mikhaylovivan2semester.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Getter
public class CreateWebsiteRequest {
  @NotBlank
  private String name;

  @URL
  @Size(min = 5, max = 30)
  private String url;

  private final UUID userId;

  public CreateWebsiteRequest(String name, String url, UUID userId) {
    this.name = name;
    this.url = url;
    this.userId = userId;
  }
}
