package ru.innopolis.java.attestation03.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.java.attestation03.dto.ResultDTO;
import ru.innopolis.java.attestation03.service.ResultsService;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultsController {

    private final ResultsService resultsService;

    @Operation(summary = "Get all results", description = "Returns a list of all results")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<Iterable<ResultDTO>> getAll() {
        return ResponseEntity.ok(resultsService.getAll());
    }

    @Operation(summary = "Get result by ID", description = "Returns the result with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved result"),
            @ApiResponse(responseCode = "404", description = "Result not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getById(@PathVariable Long id) {
        try {
            ResultDTO dto = resultsService.getResultById(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create a new result", description = "Creates a new result with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created result"),
            @ApiResponse(responseCode = "400", description = "Invalid data provided")
    })
    @PostMapping
    public ResponseEntity<ResultDTO> create(@RequestBody ResultDTO resultDTO) {
        return ResponseEntity.ok(resultsService.create(resultDTO));
    }

    @Operation(summary = "Update result by ID", description = "Updates an existing result with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated result"),
            @ApiResponse(responseCode = "404", description = "Result not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> update(@PathVariable Long id, @RequestBody ResultDTO newData) {
        return ResponseEntity.ok(resultsService.updateResult(id, newData));
    }

    @Operation(summary = "Delete result by ID", description = "Soft-deletes the result with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted result"),
            @ApiResponse(responseCode = "404", description = "Result not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        resultsService.softDeleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get results by player ID", description = "Returns a list of results for the specified player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved results")
    })
    @GetMapping("/player/{playerId}")
    public ResponseEntity<Iterable<ResultDTO>> getByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(resultsService.getResultsByPlayerId(playerId));
    }

    @Operation(summary = "Get results by player and opponent", description = "Returns results for the specified player and opponent")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved results")
    })
    @GetMapping("/player/{playerId}/opponent/{opponentId}")
    public ResponseEntity<Iterable<ResultDTO>> getByPlayerAndOpponent(@PathVariable Long playerId, @PathVariable Long opponentId) {
        return ResponseEntity.ok(resultsService.getResultsByPlayerAndOpponent(playerId, opponentId));
    }

    @Operation(summary = "Get player wins", description = "Returns all wins for the specified player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved wins")
    })
    @GetMapping("/player/{playerId}/wins")
    public ResponseEntity<Iterable<ResultDTO>> getPlayerWins(@PathVariable Long playerId) {
        return ResponseEntity.ok(resultsService.getPlayerWins(playerId));
    }

    @Operation(summary = "Get player losses", description = "Returns all losses for the specified player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved losses")
    })
    @GetMapping("/player/{playerId}/losses")
    public ResponseEntity<Iterable<ResultDTO>> getPlayerLosses(@PathVariable Long playerId) {
        return ResponseEntity.ok(resultsService.getPlayerLosses(playerId));
    }
}
