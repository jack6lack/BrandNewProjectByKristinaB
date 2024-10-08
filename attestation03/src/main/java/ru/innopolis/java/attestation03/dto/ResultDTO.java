package ru.innopolis.java.attestation03.dto;

import lombok.*;
import ru.innopolis.java.attestation03.entity.GameResult;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResultDTO {

    private Long id;
    private Long gameId;
    private Long playerId;
    private Long opponentId;
    private GameResult result;
    private LocalDateTime battleDate;

    public void setId(Long id) {
        throw new UnsupportedOperationException("Id cannot be manually set");
    }

    @Builder
    public ResultDTO(Long gameId, Long playerId, Long opponentId, GameResult result, LocalDateTime battleDate) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.opponentId = opponentId;
        this.result = result;
        this.battleDate = battleDate;
    }
}