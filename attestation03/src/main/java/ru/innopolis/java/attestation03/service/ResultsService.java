package ru.innopolis.java.attestation03.service;

import org.springframework.stereotype.Service;
import ru.innopolis.java.attestation03.dto.ResultDTO;

@Service

public interface ResultsService {
    Iterable<ResultDTO> getAll();

    ResultDTO getResultById(Long resultId);

    void deleteById(Long resultId);

    void softDeleteById(Long resultId);

    ResultDTO create(ResultDTO resultDTO);

    ResultDTO updateResult(Long resultId, ResultDTO newData);

    Iterable<ResultDTO> getResultsByPlayerId(Long playerId);

    Iterable<ResultDTO> getResultsByPlayerAndOpponent(Long playerId, Long opponentId);

    Iterable<ResultDTO> getPlayerWins(Long playerId);

    Iterable<ResultDTO> getPlayerLosses(Long playerId);
}
