package org.example.mikhaylovivan2semester.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.mikhaylovivan2semester.entity.Catalog;
import org.example.mikhaylovivan2semester.entity.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Catalog API", description = "Operations related to catalogs")
public interface CatalogApiDocumentation {

  @Operation(summary = "Get basic catalogs", description = "Fetch a list of basic catalogs")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of basic catalogs"),
      @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping("/basic")
  ResponseEntity<Response<List<Catalog>>> getBasicCatalogs();

  @Operation(summary = "Get catalogs for a user", description = "Fetch a list of catalogs associated with a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of catalogs for the user"),
      @ApiResponse(responseCode = "404", description = "User not found")
  })
  @GetMapping("/user/{userId}")
  ResponseEntity<Response<List<Catalog>>> getUserCatalogs(
      @Parameter(description = "User ID", required = true) @PathVariable UUID userId
  );

  @Operation(summary = "Check if catalog exists by name", description = "Check whether a catalog with the specified name exists")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catalog existence status"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @GetMapping("/exists")
  ResponseEntity<Response<Boolean>> existsByName(
      @Parameter(description = "Catalog name", required = true) @RequestParam String name
  );

  @Operation(summary = "Get catalog by name", description = "Fetch a catalog by its name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catalog with specified name"),
      @ApiResponse(responseCode = "404", description = "Catalog not found")
  })
  @GetMapping("/name")
  ResponseEntity<Response<Optional<Catalog>>> getByName(
      @Parameter(description = "User ID", required = true) @RequestParam UUID userId,
      @Parameter(description = "Catalog name", required = true) @RequestParam String name
  );

  @Operation(summary = "Delete catalog by name", description = "Delete a catalog by its name")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catalog deleted successfully"),
      @ApiResponse(responseCode = "404", description = "Catalog not found")
  })
  @DeleteMapping("/delete")
  ResponseEntity<Response<Void>> deleteByName(
      @Parameter(description = "User ID", required = true) @RequestParam UUID userId,
      @Parameter(description = "Catalog name", required = true) @RequestParam String name
  );

  @Operation(summary = "Add catalog to user", description = "Associate a catalog with a user by their ID")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Catalog added to user"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @PutMapping("/addToUser")
  ResponseEntity<Response<Catalog>> addToUser(
      @Parameter(description = "User ID", required = true) @RequestParam UUID userId,
      @Parameter(description = "Catalog name", required = true) @RequestParam String name
  );

  @Operation(summary = "Add a new catalog for a user", description = "Create and add a new catalog to a user")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "New catalog added to user"),
      @ApiResponse(responseCode = "400", description = "Bad request")
  })
  @PutMapping("/addUserCatalog")
  ResponseEntity<Response<Catalog>> addUserCatalog(
      @Parameter(description = "User ID", required = true) @RequestParam UUID userId,
      @Parameter(description = "Catalog name", required = true) @RequestParam String name
  );
}
