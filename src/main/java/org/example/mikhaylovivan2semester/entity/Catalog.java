package org.example.mikhaylovivan2semester.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "catalogs")
public class Catalog {
    @Id
    @Column(name = "catalog_id")
    private UUID catalogId;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "name")
    private String name;

    @Column(name = "user_id")
    private UUID userId;

    public Catalog() {}

    public Catalog(UUID catalogId, String name, UUID userId) {
        this.catalogId = catalogId;
        this.name = name;
        this.userId = userId;
    }

}

