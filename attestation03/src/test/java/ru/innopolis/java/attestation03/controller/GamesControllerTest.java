package ru.innopolis.java.attestation03.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.innopolis.java.attestation03.dto.GameDTO;
import ru.innopolis.java.attestation03.service.GamesService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GamesControllerTest {

    @Mock
    private GamesService gamesService;

    @InjectMocks
    private GamesController gamesController;

    private List<GameDTO> gameDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameDTOList = new ArrayList<>();
        gameDTOList.add(new GameDTO("Game 1", 1L, 2L));
        gameDTOList.add(new GameDTO("Game 2", 3L, 4L));
        gameDTOList.add(new GameDTO("Game 3", 5L, 6L));
    }

    @Test
    void getAll() {
        when(gamesService.getAll()).thenReturn(gameDTOList);

        ResponseEntity<Iterable<GameDTO>> response = gamesController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getById() {
        GameDTO dto = new GameDTO( "Game 1", 1L, 2L);
        when(gamesService.getGameById(1L)).thenReturn(dto);

        ResponseEntity<GameDTO> response = gamesController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals(1L, response.getBody().getPlayerId());
        assertEquals(2L, response.getBody().getOpponentId());
    }

    @Test
    void getById_NotFound() {
        when(gamesService.getGameById(99L)).thenThrow(new EntityNotFoundException());

        ResponseEntity<GameDTO> response = gamesController.getById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        GameDTO dto = new GameDTO("Game 1", 1L, 2L);
        when(gamesService.create(any(GameDTO.class))).thenReturn(dto);

        ResponseEntity<GameDTO> response = gamesController.create(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals("Game 1", response.getBody().getGameName());
        assertEquals(1L, response.getBody().getPlayerId());
        assertEquals(2L, response.getBody().getOpponentId());
    }

    @Test
    void update() {
        GameDTO dto = new GameDTO( "Game 1", 1L, 2L);
        when(gamesService.updateGame(1L, dto)).thenReturn(dto);

        ResponseEntity<GameDTO> response = gamesController.update(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals(1L, response.getBody().getPlayerId());
        assertEquals(2L, response.getBody().getOpponentId());
    }

    @Test
    void delete() {
        doNothing().when(gamesService).softDeleteById(1L);

        ResponseEntity<Void> response = gamesController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(gamesService, times(1)).softDeleteById(1L);
    }

    @Test
    void getByPlayerId() {
        when(gamesService.getGamesByPlayerId(1L)).thenReturn(gameDTOList);

        ResponseEntity<Iterable<GameDTO>> response = gamesController.getByPlayerId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getRecentGames() {
        when(gamesService.getRecentGames(5)).thenReturn(gameDTOList);

        ResponseEntity<Iterable<GameDTO>> response = gamesController.getRecentGames(5);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }
}