package ru.innopolis.java.attestation03.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.java.attestation03.entity.GameResult;
import ru.innopolis.java.attestation03.entity.Result;

import java.util.List;

public interface ResultsRepository extends CrudRepository<Result, Long> {

    List<Result> findAllByPlayerId(Long playerId);

    List<Result> findAllByPlayerIdAndOpponentId(Long playerId, Long opponentId);

    List<Result> findAllByPlayerIdAndResult(Long playerId, GameResult result);
}
