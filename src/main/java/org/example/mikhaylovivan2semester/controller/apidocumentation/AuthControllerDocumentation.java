package org.example.mikhaylovivan2semester.controller.apidocumentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.mikhaylovivan2semester.dto.Response;
import org.example.mikhaylovivan2semester.dto.request.CreateUserRequest;
import org.example.mikhaylovivan2semester.dto.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Authentication API", description = "Operations related to user authentication")
public interface AuthControllerDocumentation {

  @Operation(summary = "User registration", description = "Registers a new user in the system")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "User registered successfully"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @PostMapping("/register")
  ResponseEntity<Response<Object>> register(
      @RequestBody(description = "User registration request", required = true) CreateUserRequest registerRequest
  );

  @Operation(summary = "User login", description = "Authenticates a user and returns a JWT token")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Login successful, returns JWT token"),
      @ApiResponse(responseCode = "401", description = "Unauthorized - invalid credentials"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @PostMapping("/login")
  ResponseEntity<Response<String>> login(
      @RequestBody(description = "User login request", required = true) LoginRequest loginRequest
  );
}
