package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.exceptions.InvalidIDException;
import ru.innopolis.java.attestation.attestation01.exceptions.InvalidNameFormatException;
import ru.innopolis.java.attestation.attestation01.exceptions.LoginAlreadyTakenException;
import ru.innopolis.java.attestation.attestation01.model.User;

import java.io.IOException;
import java.util.List;

public interface UsersRepository {
    void create(User user) throws LoginAlreadyTakenException, InvalidNameFormatException;

    User findById(String id) throws IOException, InvalidIDException;

    List<User> findAll();

    void update(User user) throws IOException;

    void deleteById(String id) throws IOException, InvalidIDException;

    void deleteAll();

    void printBeautifulLines(List<User> list, String fileName);
}
