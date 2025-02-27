package org.example.mikhaylovivan2semester.dto.request.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateArticleRequest {
    @NotBlank
    @Size(min = 3, max = 255)
    private String title;

    @NotBlank
    @Size(min = 10, max = 500)
    private String description;

    @NotBlank
    private String date;

    @NotBlank
    private String link;

    public CreateArticleRequest(String title, String description, String date, String link) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.link = link;
    }
}
