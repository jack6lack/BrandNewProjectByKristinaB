package ru.innopolis.java.attestation03.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innopolis.java.attestation03.entity.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {

    Optional<User> findByCharacterName(String name);
}
