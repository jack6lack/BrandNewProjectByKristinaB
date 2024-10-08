package ru.innopolis.java.attestation03.service;

import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.GameDTO;

@Service

public interface GamesService {
    Iterable<GameDTO> getAll();

    GameDTO getGameById(Long gameId);

    void deleteById(Long gameId);

    void softDeleteById(Long gameId);

    GameDTO create(GameDTO gameDTO);

    GameDTO updateGame(Long gameId, GameDTO newData);

    Iterable<GameDTO> getGamesByPlayerId(Long playerId);

    Iterable<GameDTO> getRecentGames(int limit);
}
