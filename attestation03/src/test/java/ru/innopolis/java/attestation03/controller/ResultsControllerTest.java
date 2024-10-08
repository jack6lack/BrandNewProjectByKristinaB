package ru.innopolis.java.attestation03.controller;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.innopolis.java.attestation03.dto.ResultDTO;
import ru.innopolis.java.attestation03.entity.GameResult;
import ru.innopolis.java.attestation03.service.ResultsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResultsControllerTest {

    @Mock
    private ResultsService resultsService;

    @InjectMocks
    private ResultsController resultsController;

    private List<ResultDTO> resultDTOList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        resultDTOList = new ArrayList<>();
        resultDTOList.add(new ResultDTO(1L, 1L, 2L, GameResult.WIN, LocalDateTime.parse("2024-08-12T16:15:49.372997600")));
        resultDTOList.add(new ResultDTO(2L, 3L, 4L, GameResult.LOSS, LocalDateTime.parse("2024-08-12T16:15:49.372997600")));
        resultDTOList.add(new ResultDTO(3L, 5L, 6L, GameResult.DRAW, LocalDateTime.parse("2024-08-12T16:15:49.372997600")));
    }

    @Test
    void getAll() {
        when(resultsService.getAll()).thenReturn(resultDTOList);

        ResponseEntity<Iterable<ResultDTO>> response = resultsController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getById() {
        ResultDTO dto = new ResultDTO(1L, 1L, 2L, GameResult.WIN, LocalDateTime.parse("2024-08-12T16:15:49.372997600"));
        when(resultsService.getResultById(1L)).thenReturn(dto);

        ResponseEntity<ResultDTO> response = resultsController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals(1L, response.getBody().getPlayerId());
        assertEquals(2L, response.getBody().getOpponentId());
    }

    @Test
    void getById_NotFound() {
        when(resultsService.getResultById(99L)).thenThrow(new EntityNotFoundException());

        ResponseEntity<ResultDTO> response = resultsController.getById(99L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void create() {
        ResultDTO dto = new ResultDTO(1L, 1L, 2L, GameResult.WIN, LocalDateTime.parse("2024-08-12T16:15:49.372997600"));
        when(resultsService.create(any(ResultDTO.class))).thenReturn(dto);

        ResponseEntity<ResultDTO> response = resultsController.create(dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals(GameResult.WIN, response.getBody().getResult());
        assertEquals(1L, response.getBody().getPlayerId());
        assertEquals(2L, response.getBody().getOpponentId());
    }

    @Test
    void update() {
        ResultDTO dto = new ResultDTO(1L, 1L, 2L, GameResult.WIN, LocalDateTime.parse("2024-08-12T16:15:49.372997600"));
        when(resultsService.updateResult(1L, dto)).thenReturn(dto);

        ResponseEntity<ResultDTO> response = resultsController.update(1L, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
        assertEquals(1L, response.getBody().getPlayerId());
        assertEquals(2L, response.getBody().getOpponentId());
    }

    @Test
    void delete() {
        doNothing().when(resultsService).softDeleteById(1L);

        ResponseEntity<Void> response = resultsController.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(resultsService, times(1)).softDeleteById(1L);
    }

    @Test
    void getByPlayerId() {
        when(resultsService.getResultsByPlayerId(1L)).thenReturn(resultDTOList);

        ResponseEntity<Iterable<ResultDTO>> response = resultsController.getByPlayerId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getByPlayerAndOpponent() {
        when(resultsService.getResultsByPlayerAndOpponent(1L, 2L)).thenReturn(resultDTOList);

        ResponseEntity<Iterable<ResultDTO>> response = resultsController.getByPlayerAndOpponent(1L, 2L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getPlayerWins() {
        when(resultsService.getPlayerWins(1L)).thenReturn(resultDTOList);

        ResponseEntity<Iterable<ResultDTO>> response = resultsController.getPlayerWins(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }

    @Test
    void getPlayerLosses() {
        when(resultsService.getPlayerLosses(1L)).thenReturn(resultDTOList);

        ResponseEntity<Iterable<ResultDTO>> response = resultsController.getPlayerLosses(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(3, ((Collection<?>) response.getBody()).size());
    }
}
