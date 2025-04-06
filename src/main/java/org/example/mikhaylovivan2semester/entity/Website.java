package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Entity
@Table(name = "websites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Website {

  @Id
  @Column(name = "website_id", updatable = false, nullable = false)
  private UUID id;

  @NotBlank
  @Size(min = 3, max = 100)
  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @URL
  @Size(min = 5, max = 255)
  @Column(name = "url", nullable = false, length = 255)
  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public Website(String name, String url, User user) {
    this.id = UUID.randomUUID();
    this.name = name;
    this.url = url;
    this.user = user;
  }
}
