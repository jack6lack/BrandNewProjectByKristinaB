package ru.innopolis.java.attestation03.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class GameDTO {

    private Long id;
    private String gameName;
    private Long playerId;
    private Long opponentId;

    public void setId(Long id) {
        throw new UnsupportedOperationException("Id cannot be manually set");
    }

    @Builder
    public GameDTO(String gameName, Long playerId, Long opponentId) {
        this.gameName = gameName;
        this.playerId = playerId;
        this.opponentId = opponentId;
    }
}
