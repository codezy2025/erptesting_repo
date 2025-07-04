package com.example.demo.module2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import com.java.coreTemplate.service.NewmoduleService;
import com.java.coreTemplate.model.dto.Newmodule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/newmodule")
@Tag(name = "Newmodule", description = "Operations related to Newmodule management")
public class NewmoduleController {
    private final NewmoduleService service;

    public NewmoduleController(NewmoduleService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create a new Newmodule")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Newmodule created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Newmodule> create(@Valid @RequestBody Newmodule entity) {
        Newmodule savedEntity = service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Newmodule by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Newmodule found"),
        @ApiResponse(responseCode = "404", description = "Newmodule not found")
    })
    public ResponseEntity<Newmodule> getById(
            @Parameter(description = "ID of Newmodule to be retrieved") @PathVariable Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all Newmodules with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Newmodules retrieved successfully")
    })
    public ResponseEntity<Page<Newmodule>> getAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Newmodule")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Newmodule updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Newmodule not found")
    })
    public ResponseEntity<Newmodule> update(
            @Parameter(description = "ID of Newmodule to be updated") @PathVariable Long id,
            @Valid @RequestBody Newmodule entity) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        entity.setId(id); // Ensure the ID is set for update
        return ResponseEntity.ok(service.save(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Newmodule")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Newmodule deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Newmodule not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of Newmodule to be deleted") @PathVariable Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search Newmodules with pagination")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search results returned successfully")
    })
    public ResponseEntity<Page<Newmodule>> search(
            @Parameter(description = "Search query") @RequestParam(required = false) String query,
            Pageable pageable) {
        return ResponseEntity.ok(service.search(query, pageable));
    }
}