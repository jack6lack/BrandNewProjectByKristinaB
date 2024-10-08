package ru.innopolis.java.attestation03.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innopolis.java.attestation03.entity.Characteristics;

import java.util.Optional;

@Repository

public interface CharacteristicsRepository extends CrudRepository<Characteristics, Long> {

    Optional<Characteristics> findByUserId(Long userId);
}
