package ru.innopolis.java.attestation03.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.innopolis.java.attestation03.dto.ResultDTO;
import ru.innopolis.java.attestation03.entity.Game;
import ru.innopolis.java.attestation03.entity.GameResult;
import ru.innopolis.java.attestation03.entity.Result;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.GamesRepository;
import ru.innopolis.java.attestation03.repository.ResultsRepository;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.impl.ResultsServiceImpl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ResultsServiceTest {

    @Mock
    private ResultsRepository resultsRepository;

    @Mock
    private GamesRepository gamesRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private ResultsServiceImpl resultsService;

    private Result result;
    private ResultDTO resultDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        User player = new User(1L, "PlayerName", "Race1", "Class1", false);
        User opponent = new User(2L, "OpponentName", "Race2", "Class2", false);
        Game game = new Game(1L, "game", player, opponent, false);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(player));
        when(usersRepository.findById(2L)).thenReturn(Optional.of(opponent));
        when(gamesRepository.findById(1L)).thenReturn(Optional.of(game));

        result = new Result(1L, game, player, opponent, GameResult.DRAW, LocalDateTime.now(), false);
        resultDTO = new ResultDTO(game.getId(), player.getId(), opponent.getId(), GameResult.WIN, LocalDateTime.now());
    }

    @Test
    void testGetAll() {
        when(resultsRepository.findAll()).thenReturn(List.of(result));

        Iterable<ResultDTO> resultList = resultsService.getAll();

        assertNotNull(resultList);
        assertEquals(1, ((Collection<?>) resultList).size());
    }

    @Test
    void testGetResultById() {
        when(resultsRepository.findById(1L)).thenReturn(Optional.of(result));

        ResultDTO retrievedResult = resultsService.getResultById(1L);

        assertNotNull(retrievedResult);
        assertEquals(GameResult.DRAW, retrievedResult.getResult());
    }

    @Test
    void testCreate() {
        when(resultsRepository.save(any(Result.class))).thenReturn(result);

        ResultDTO createdResult = resultsService.create(resultDTO);

        assertNotNull(createdResult);
        assertEquals(GameResult.DRAW, createdResult.getResult());
        verify(resultsRepository, times(1)).save(any(Result.class));
    }

    @Test
    void testUpdateResult() {
        when(resultsRepository.findById(1L)).thenReturn(Optional.of(result));
        when(resultsRepository.save(any(Result.class))).thenReturn(result);

        ResultDTO updatedResult = resultsService.updateResult(1L, resultDTO);

        assertNotNull(updatedResult);
        assertEquals(GameResult.WIN, updatedResult.getResult());
        verify(resultsRepository, times(1)).save(any(Result.class));
    }

    @Test
    void testDeleteById() {
        when(resultsRepository.findById(1L)).thenReturn(Optional.of(result));
        doNothing().when(resultsRepository).delete(result);

        resultsService.deleteById(1L);

        verify(resultsRepository, times(1)).findById(1L);
        verify(resultsRepository, times(1)).delete(result);
    }

    @Test
    void testSoftDeleteById() {
        when(resultsRepository.findById(1L)).thenReturn(Optional.of(result));
        resultsService.softDeleteById(1L);

        verify(resultsRepository, times(1)).save(result);
        assertTrue(result.isDeleted());
    }

    @Test
    void testGetResultsByPlayerId() {
        when(resultsRepository.findAllByPlayerId(1L)).thenReturn(List.of(result));

        Iterable<ResultDTO> resultList = resultsService.getResultsByPlayerId(1L);

        assertNotNull(resultList);
        assertEquals(1, ((Collection<?>) resultList).size());
    }

    @Test
    void testGetResultsByPlayerAndOpponent() {
        when(resultsRepository.findAllByPlayerIdAndOpponentId(1L, 2L)).thenReturn(List.of(result));

        Iterable<ResultDTO> resultList = resultsService.getResultsByPlayerAndOpponent(1L, 2L);

        assertNotNull(resultList);
        assertEquals(1, ((Collection<?>) resultList).size());
    }

    @Test
    void testGetPlayerWins() {
        when(resultsRepository.findAllByPlayerIdAndResult(1L, GameResult.WIN)).thenReturn(List.of(result));

        Iterable<ResultDTO> resultList = resultsService.getPlayerWins(1L);

        assertNotNull(resultList);
        assertEquals(1, ((Collection<?>) resultList).size());
    }

    @Test
    void testGetPlayerLosses() {
        when(resultsRepository.findAllByPlayerIdAndResult(1L, GameResult.LOSS)).thenReturn(List.of(result));

        Iterable<ResultDTO> resultList = resultsService.getPlayerLosses(1L);

        assertNotNull(resultList);
        assertEquals(1, ((Collection<?>) resultList).size());
    }
}
