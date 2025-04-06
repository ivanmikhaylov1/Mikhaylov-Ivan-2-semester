package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_last_request")
@Getter
@Setter
@NoArgsConstructor
public class UserLastRequest {

  @Id
  @Column(name = "user_id", updatable = false, nullable = false)
  private UUID userId;

  @Column(name = "last_request_time", nullable = false)
  private LocalDateTime lastRequestTime;

  @OneToOne(fetch = FetchType.LAZY)
  @MapsId
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  public UserLastRequest(User user, LocalDateTime lastRequestTime) {
    this.user = user;
    this.userId = user.getId();
    this.lastRequestTime = lastRequestTime;
  }
}
