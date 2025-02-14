package org.example.mikhaylovivan2semester.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.mikhaylovivan2semester.entity.Response;
import org.example.mikhaylovivan2semester.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Tag(name = "Users", description = "Operations related to Users")
public interface UserApiDocumentation {

  @Operation(summary = "Get all users", description = "Fetch all users from the system")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of users"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  ResponseEntity<Response<List<User>>> findAll();

  @Operation(summary = "Create a new user", description = "Create a new user in the system")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User created successfully"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  ResponseEntity<Response<User>> save(
      @Parameter(description = "User name", required = true)
      @NotBlank @Size(min = 3, max = 50) @RequestParam String name,

      @Parameter(description = "User password", required = true)
      @NotBlank @Size(min = 6) @RequestParam String password);

  @Operation(summary = "Get user by ID", description = "Fetch user details by their ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User details"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  ResponseEntity<Response<User>> getById(@PathVariable UUID userId);

  @Operation(summary = "Get user by name", description = "Fetch user details by their name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User details"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  ResponseEntity<Response<User>> findByName(@RequestParam @NotBlank String name);

  @Operation(summary = "Check if user exists", description = "Check if a user exists by their name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User exists"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  ResponseEntity<Response<Boolean>> exists(@RequestParam @NotBlank String name);
}
