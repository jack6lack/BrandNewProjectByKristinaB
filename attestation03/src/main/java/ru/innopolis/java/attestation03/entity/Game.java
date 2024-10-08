package ru.innopolis.java.attestation03.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameName;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private User player;

    @ManyToOne
    @JoinColumn(name = "opponent_id", referencedColumnName = "id")
    private User opponent;

    private boolean deleted;
}
