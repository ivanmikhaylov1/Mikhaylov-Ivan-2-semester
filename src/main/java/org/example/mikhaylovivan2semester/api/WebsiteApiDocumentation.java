package org.example.mikhaylovivan2semester.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.mikhaylovivan2semester.entity.Response;
import org.example.mikhaylovivan2semester.entity.Website;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Website API", description = "Operations related to websites")
public interface WebsiteApiDocumentation {

  @Operation(summary = "Get basic websites", description = "Fetch a list of basic websites")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of basic websites"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/basic")
  ResponseEntity<Response<List<Website>>> getBasicWebsites();

  @Operation(summary = "Get websites for a user", description = "Fetch a list of websites associated with a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of websites for the user"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @GetMapping("/user/{userId}")
  ResponseEntity<Response<List<Website>>> getUserWebsites(
      @Parameter(description = "User ID", required = true) @PathVariable UUID userId
  );

  @Operation(summary = "Add a website for a user", description = "Add a website for a specified user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Website successfully added"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @PostMapping("/user/{userId}")
  ResponseEntity<Response<Website>> addUserWebsite(
      @Parameter(description = "User ID", required = true) @PathVariable UUID userId,
      @Parameter(description = "Website name", required = true) @NotBlank @Size(min = 3, max = 100) @RequestParam String name,
      @Parameter(description = "Website URL", required = true) @NotBlank @Size(min = 5, max = 200) @RequestParam String url
  );

  @Operation(summary = "Delete a website by name", description = "Delete a website for a specified user by name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Website successfully deleted"),
      @ApiResponse(responseCode = "404", description = "Website not found")
  })
  @DeleteMapping("/user/{userId}")
  ResponseEntity<Response<Void>> deleteByName(
      @Parameter(description = "User ID", required = true) @PathVariable UUID userId,
      @Parameter(description = "Website name", required = true) @NotBlank @RequestParam String name
  );

  @Operation(summary = "Check if a website exists by name", description = "Check whether a website with the specified name exists")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Website existence status"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @GetMapping("/exists")
  ResponseEntity<Response<Boolean>> existsByName(
      @Parameter(description = "Website name", required = true) @RequestParam @NotBlank String name
  );

  @Operation(summary = "Get a website by name", description = "Fetch a website by its name for a specific user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Website details"),
      @ApiResponse(responseCode = "404", description = "Website not found")
  })
  @GetMapping("/user/{userId}/by-name")
  ResponseEntity<Response<Website>> getByName(
      @Parameter(description = "User ID", required = true) @PathVariable UUID userId,
      @Parameter(description = "Website name", required = true) @NotBlank @RequestParam String name
  );
}
