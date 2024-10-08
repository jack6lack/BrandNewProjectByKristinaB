package ru.innopolis.java.attestation03.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.GameDTO;
import ru.innopolis.java.attestation03.entity.Game;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.GamesRepository;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.GamesService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GamesServiceImpl implements GamesService {

    private final GamesRepository gamesRepository;
    private final UsersRepository usersRepository;

    @Override
    public Iterable<GameDTO> getAll() {
        return convertToDTOList(gamesRepository.findAll());
    }

    @Override
    public GameDTO getGameById(Long gameId) {
        Game game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        return convertToDTO(game);
    }

    @Override
    public void deleteById(Long gameId) {
        Game game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        gamesRepository.delete(game);
    }

    @Override
    public void softDeleteById(Long gameId) {
        Game game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        game.setDeleted(true);
        gamesRepository.save(game);
    }

    @Override
    public GameDTO create(GameDTO gameDTO) {
        Game game = convertToEntity(gameDTO);
        Game savedGame = gamesRepository.save(game);
        return convertToDTO(savedGame);
    }

    @Override
    public GameDTO updateGame(Long gameId, GameDTO newData) {
        Game game = gamesRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        game.setGameName(newData.getGameName());
        if (!game.getPlayer().getId().equals(newData.getPlayerId())) {
            User user = usersRepository.findById(newData.getPlayerId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            game.setPlayer(user);
        }
        if (!game.getOpponent().getId().equals(newData.getOpponentId())) {
            User user = usersRepository.findById(newData.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            game.setOpponent(user);
        }
        return convertToDTO(gamesRepository.save(game));
    }

    @Override
    public Iterable<GameDTO> getGamesByPlayerId(Long playerId) {
        return gamesRepository.findAllByPlayerOrOpponent(playerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<GameDTO> getRecentGames(int limit) {
        return gamesRepository.findTopByOrderByGameNameDesc(PageRequest.of(0, limit))
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private GameDTO convertToDTO(Game game) {
        return GameDTO.builder()
                .gameName(game.getGameName())
                .playerId(game.getPlayer().getId())
                .opponentId(game.getOpponent().getId())
                .build();
    }

    private Game convertToEntity(GameDTO gameDTO) {
        Game game = new Game();
        game.setId(gameDTO.getId());
        game.setGameName(gameDTO.getGameName());
        User player = usersRepository.findById(gameDTO.getPlayerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        User opponent = usersRepository.findById(gameDTO.getOpponentId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        game.setPlayer(player);
        game.setOpponent(opponent);
        return game;
    }

    private Iterable<GameDTO> convertToDTOList(Iterable<Game> gameList) {
        List<GameDTO> dtoList = new ArrayList<>();
        gameList.forEach(game -> dtoList.add(convertToDTO(game)));
        return dtoList;
    }
}
