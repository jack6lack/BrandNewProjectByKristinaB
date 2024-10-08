package ru.innopolis.java.attestation03.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.innopolis.java.attestation03.entity.Game;

import java.util.List;

public interface GamesRepository extends CrudRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE g.player.id = :id OR g.opponent.id = :id")
    List<Game> findAllByPlayerOrOpponent(@Param("id") Long id);


    List<Game> findTopByOrderByGameNameDesc(Pageable pageable);
}
