package ru.innopolis.java.attestation03.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.java.attestation03.dto.CharacteristicsDTO;
import ru.innopolis.java.attestation03.service.CharacteristicsService;

@RestController
@RequestMapping("/api/characteristics")
@RequiredArgsConstructor
public class CharacteristicsController {

    private final CharacteristicsService characteristicsService;

    @Operation(summary = "Get all characteristics", description = "Returns a list of all characteristics")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping
    public ResponseEntity<Iterable<CharacteristicsDTO>> getAll() {
        return ResponseEntity.ok(characteristicsService.getAll());
    }

    @Operation(summary = "Get characteristic by ID", description = "Returns characteristic with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved characteristic"),
            @ApiResponse(responseCode = "404", description = "Characteristic not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CharacteristicsDTO> getById(@PathVariable Long id) {
        try {
            CharacteristicsDTO dto = characteristicsService.getCharacteristicById(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Operation(summary = "Create a new characteristic", description = "Creates a new characteristic with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created characteristic"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<CharacteristicsDTO> create(@RequestBody CharacteristicsDTO characteristicsDTO) {
        return ResponseEntity.ok(characteristicsService.create(characteristicsDTO));
    }

    @Operation(summary = "Update a characteristic by ID", description = "Updates an existing characteristic with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated characteristic"),
            @ApiResponse(responseCode = "404", description = "Characteristic not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CharacteristicsDTO> update(@PathVariable Long id, @RequestBody CharacteristicsDTO newData) {
        return ResponseEntity.ok(characteristicsService.updateCharacteristic(id, newData));
    }

    @Operation(summary = "Delete a characteristic by ID", description = "Soft-deletes the characteristic with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted characteristic"),
            @ApiResponse(responseCode = "404", description = "Characteristic not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characteristicsService.softDeleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update strength by user ID", description = "Updates the strength attribute for the specified user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated strength"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PatchMapping("/{userId}/strength")
    public ResponseEntity<CharacteristicsDTO> updateStrength(@PathVariable Long userId, @RequestParam int newStrength) {
        return ResponseEntity.ok(characteristicsService.updateStrength(userId, newStrength));
    }
}
