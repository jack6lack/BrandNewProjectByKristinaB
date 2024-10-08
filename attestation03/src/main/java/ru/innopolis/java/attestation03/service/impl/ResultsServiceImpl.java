package ru.innopolis.java.attestation03.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.ResultDTO;
import ru.innopolis.java.attestation03.entity.GameResult;
import ru.innopolis.java.attestation03.entity.Result;
import ru.innopolis.java.attestation03.entity.User;
import ru.innopolis.java.attestation03.repository.GamesRepository;
import ru.innopolis.java.attestation03.repository.ResultsRepository;
import ru.innopolis.java.attestation03.repository.UsersRepository;
import ru.innopolis.java.attestation03.service.ResultsService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultsServiceImpl implements ResultsService {

    private final ResultsRepository resultsRepository;
    private final GamesRepository gamesRepository;
    private final UsersRepository usersRepository;

    @Override
    public Iterable<ResultDTO> getAll() {
        return convertToDTOList(resultsRepository.findAll());
    }

    @Override
    public ResultDTO getResultById(Long resultId) {
        Result result = resultsRepository.findById(resultId)
                .orElseThrow(() -> new EntityNotFoundException("Result not found"));
        return convertToDTO(result);
    }

    @Override
    public void deleteById(Long resultId) {
        Result result = resultsRepository.findById(resultId)
                .orElseThrow(() -> new EntityNotFoundException("Result not found"));
        resultsRepository.delete(result);
    }

    @Override
    public void softDeleteById(Long resultId) {
        Result result = resultsRepository.findById(resultId)
                .orElseThrow(() -> new EntityNotFoundException("Result not found"));
        result.setDeleted(true);
        resultsRepository.save(result);
    }

    @Override
    public ResultDTO create(ResultDTO resultDTO) {
        Result result = convertToEntity(resultDTO);
        Result savedResult = resultsRepository.save(result);
        return convertToDTO(savedResult);
    }

    @Override
    public ResultDTO updateResult(Long resultId, ResultDTO newData) {
        Result result = resultsRepository.findById(resultId)
                .orElseThrow(() -> new EntityNotFoundException("Result not found"));
        result.setGame(gamesRepository.findById(newData.getGameId()).orElse(null));
        if (!result.getPlayer().getId().equals(newData.getPlayerId())) {
            User user = usersRepository.findById(newData.getPlayerId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            result.setPlayer(user);
        }
        if (!result.getOpponent().getId().equals(newData.getOpponentId())) {
            User user = usersRepository.findById(newData.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            result.setOpponent(user);
        }
        result.setResult(newData.getResult());
        result.setBattleDate(newData.getBattleDate());
        return convertToDTO(resultsRepository.save(result));
    }

    @Override
    public Iterable<ResultDTO> getResultsByPlayerId(Long playerId) {

        return resultsRepository.findAllByPlayerId(playerId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ResultDTO> getResultsByPlayerAndOpponent(Long playerId, Long opponentId) {

        return resultsRepository.findAllByPlayerIdAndOpponentId(playerId, opponentId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ResultDTO> getPlayerWins(Long playerId) {

        return resultsRepository.findAllByPlayerIdAndResult(playerId, GameResult.WIN)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<ResultDTO> getPlayerLosses(Long playerId) {

        return resultsRepository.findAllByPlayerIdAndResult(playerId, GameResult.LOSS)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ResultDTO convertToDTO(Result result) {

        return ResultDTO.builder()
                .gameId(result.getGame().getId())
                .playerId(result.getPlayer().getId())
                .opponentId(result.getOpponent().getId())
                .result(result.getResult())
                .battleDate(result.getBattleDate())
                .build();
    }

    private Result convertToEntity(ResultDTO resultDTO) {
        Result result = new Result();
        result.setId(resultDTO.getId());
        result.setGame(gamesRepository.findById(resultDTO.getGameId()).orElse(null));
        User player = usersRepository.findById(resultDTO.getPlayerId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        User opponent = usersRepository.findById(resultDTO.getOpponentId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        result.setPlayer(player);
        result.setOpponent(opponent);
        result.setResult(resultDTO.getResult());
        result.setBattleDate(resultDTO.getBattleDate());
        return result;
    }

    private Iterable<ResultDTO> convertToDTOList(Iterable<Result> resultList) {
        List<ResultDTO> dtoList = new ArrayList<>();
        resultList.forEach(result -> dtoList.add(convertToDTO(result)));
        return dtoList;
    }
}
