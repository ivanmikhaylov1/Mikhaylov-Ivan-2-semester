package org.example.mikhaylovivan2semester.controller.apidocumentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.mikhaylovivan2semester.dto.Response;
import org.example.mikhaylovivan2semester.dto.request.create.CreateArticleRequest;
import org.example.mikhaylovivan2semester.entity.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Tag(name = "Article API", description = "Operations related to articles")
public interface ArticleApiDocumentation {

  @Operation(summary = "Get new articles", description = "Fetch all articles from the system")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of articles"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<Response<List<Article>>> getNewArticles();

  @Operation(summary = "Create a new article", description = "Create a new article in the system")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Article created successfully"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  ResponseEntity<Response<String>> saveArticle(@Valid @RequestBody CreateArticleRequest createArticleRequest);

  @Operation(summary = "Update the last request time", description = "Update the last request time for the user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully updated the last request time")
  })
  ResponseEntity<Response<String>> updateUserLastRequestTime(
      @Parameter(description = "User ID", required = true) @PathVariable UUID userId
  );

  @Operation(summary = "Get the last request time for a user", description = "Fetch the last request time for the user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the last request time"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  ResponseEntity<Response<Date>> getUserLastRequestTime(
      @Parameter(description = "User ID", required = true) @PathVariable UUID userId
  );

  @Operation(summary = "Save article category", description = "Associate an article with a category")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Article category saved successfully"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  ResponseEntity<Response<String>> saveArticleCategory(
      @Parameter(description = "Article ID", required = true) @NotNull @RequestParam UUID articleId,
      @Parameter(description = "Catalog ID", required = true) @NotNull @RequestParam UUID catalogId,
      @Parameter(description = "Website ID", required = true) @NotNull @RequestParam UUID websiteId
  );
}
