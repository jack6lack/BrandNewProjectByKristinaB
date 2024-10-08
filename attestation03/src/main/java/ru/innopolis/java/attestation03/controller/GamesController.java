package ru.innopolis.java.attestation03.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.java.attestation03.dto.GameDTO;
import ru.innopolis.java.attestation03.service.GamesService;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GamesController {

    private final GamesService gamesService;

    @Operation(summary = "Get all games", description = "Returns a list of all games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @GetMapping
    public ResponseEntity<Iterable<GameDTO>> getAll() {
        return ResponseEntity.ok(gamesService.getAll());
    }

    @Operation(summary = "Get game by ID", description = "Returns the game with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved game"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GameDTO> getById(@PathVariable Long id) {
        try {
            GameDTO dto = gamesService.getGameById(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Create a new game", description = "Creates a new game with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created game"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<GameDTO> create(@RequestBody GameDTO gameDTO) {
        return ResponseEntity.ok(gamesService.create(gameDTO));
    }

    @Operation(summary = "Update game by ID", description = "Updates an existing game with the provided data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated game"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> update(@PathVariable Long id, @RequestBody GameDTO newData) {
        return ResponseEntity.ok(gamesService.updateGame(id, newData));
    }

    @Operation(summary = "Delete game by ID", description = "Soft-deletes the game with the specified ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted game"),
            @ApiResponse(responseCode = "404", description = "Game not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gamesService.softDeleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get games by player ID", description = "Returns a list of games for the specified player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved games"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @GetMapping("/player/{playerId}")
    public ResponseEntity<Iterable<GameDTO>> getByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(gamesService.getGamesByPlayerId(playerId));
    }

    @Operation(summary = "Get recent games", description = "Returns a list of recent games, limited by the specified number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved recent games"),
            @ApiResponse(responseCode = "400", description = "Invalid limit parameter")
    })
    @GetMapping("/recent")
    public ResponseEntity<Iterable<GameDTO>> getRecentGames(@RequestParam int limit) {
        return ResponseEntity.ok(gamesService.getRecentGames(limit));
    }
}
