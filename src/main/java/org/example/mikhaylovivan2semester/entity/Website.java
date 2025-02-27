package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Entity
@Table(name = "websites")
public class Website {
    @Id
    @Column(name = "website_id")
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @URL
    @Size(min = 5, max = 30)
    @Column(name = "url")
    private String url;

    @Column(name = "user_id")
    private UUID userId;

    public Website() {}

    public Website(UUID id, String name, String url, UUID userId) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
