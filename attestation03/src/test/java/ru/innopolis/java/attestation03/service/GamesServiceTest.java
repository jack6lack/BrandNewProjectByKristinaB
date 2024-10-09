package ru.innopolis.java.attestation03.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import ru.innopolis.java.attestation03.dto.GameDTO;
import ru.innopolis.java.attestation03.entity.Game;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.GamesRepository;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.impl.GamesServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GamesServiceTest {

    @Mock
    private GamesRepository gamesRepository;

    @Mock
    private UsersRepository usersRepository;

    @InjectMocks
    private GamesServiceImpl gamesService;

    private Game game;
    private User player;
    private User opponent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        player = new User(1L, "PlayerName", "Race1", "Class1", false);
        opponent = new User(2L, "OpponentName", "Race2", "Class2", false);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(player));
        when(usersRepository.findById(2L)).thenReturn(Optional.of(opponent));

        game = new Game(1L, "GameName", player, opponent, false);
    }

    @Test
    void testGetAll() {
        when(gamesRepository.findAll()).thenReturn(List.of(game));

        Iterable<GameDTO> result = gamesService.getAll();

        assertNotNull(result);
        assertEquals(1, ((Collection<?>) result).size());
    }

    @Test
    void testGetGameById() {
        when(gamesRepository.findById(1L)).thenReturn(Optional.of(game));

        GameDTO result = gamesService.getGameById(1L);

        assertNotNull(result);
        assertEquals("GameName", result.getGameName());
    }

    @Test
    void testCreate() {
        when(gamesRepository.save(any(Game.class))).thenReturn(game);

        GameDTO dto = new GameDTO("GameName", 1L, 2L);
        GameDTO result = gamesService.create(dto);

        assertNotNull(result);
        assertEquals("GameName", result.getGameName());
        verify(gamesRepository, times(1)).save(any(Game.class));
    }

    @Test
    void testUpdateGame() {
        when(gamesRepository.findById(1L)).thenReturn(Optional.of(game));
        when(gamesRepository.save(any(Game.class))).thenReturn(game);

        GameDTO newDto = new GameDTO("UpdatedGameName", 1L, 2L);
        GameDTO result = gamesService.updateGame(1L, newDto);

        assertNotNull(result);
        assertEquals("UpdatedGameName", result.getGameName());
        verify(gamesRepository, times(1)).save(any(Game.class));
    }

    @Test
    void testDeleteById() {
        when(gamesRepository.findById(1L)).thenReturn(Optional.of(game));
        doNothing().when(gamesRepository).delete(game);

        gamesService.deleteById(1L);

        verify(gamesRepository, times(1)).findById(1L);
        verify(gamesRepository, times(1)).delete(game);
    }

    @Test
    void testSoftDeleteById() {
        when(gamesRepository.findById(1L)).thenReturn(Optional.of(game));
        gamesService.softDeleteById(1L);

        verify(gamesRepository, times(1)).save(game);
        assertTrue(game.isDeleted());
    }

    @Test
    void testGetGamesByPlayerId() {
        when(gamesRepository.findAllByPlayerOrOpponent(1L)).thenReturn(List.of(game));

        Iterable<GameDTO> result = gamesService.getGamesByPlayerId(1L);

        assertNotNull(result);
        assertEquals(1, ((Collection<?>) result).size());
    }

    @Test
    void testGetRecentGames() {
        PageRequest pageRequest = PageRequest.of(0, 5);
        when(gamesRepository.findTopByOrderByGameNameDesc(pageRequest)).thenReturn(List.of(game));

        Iterable<GameDTO> result = gamesService.getRecentGames(5);

        assertNotNull(result);
        assertEquals(1, ((Collection<?>) result).size());
        verify(gamesRepository, times(1)).findTopByOrderByGameNameDesc(pageRequest);
    }

}