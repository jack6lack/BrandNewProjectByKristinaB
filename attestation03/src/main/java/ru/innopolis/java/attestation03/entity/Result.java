package ru.innopolis.java.attestation03.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private User player;

    @ManyToOne
    @JoinColumn(name = "opponent_id", referencedColumnName = "id")
    private User opponent;

    @Enumerated(EnumType.STRING)
    private GameResult result;

    private LocalDateTime battleDate;

    private boolean deleted;
}
