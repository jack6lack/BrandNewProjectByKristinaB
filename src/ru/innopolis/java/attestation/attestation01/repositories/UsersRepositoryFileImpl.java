package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.exceptions.InvalidIDException;
import ru.innopolis.java.attestation.attestation01.exceptions.InvalidNameFormatException;
import ru.innopolis.java.attestation.attestation01.exceptions.LoginAlreadyTakenException;
import ru.innopolis.java.attestation.attestation01.model.User;
import ru.innopolis.java.attestation.attestation01.support.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static ru.innopolis.java.attestation.attestation01.support.DataWriterUtils.readLineMakeUser;
import static ru.innopolis.java.attestation.attestation01.support.DataWriterUtils.readUserMakeLine;

public class UsersRepositoryFileImpl implements UsersRepository {

    private static final UsersRepositoryFileImpl INSTANCE = new UsersRepositoryFileImpl();

    private static final String FILE_NAME = "src/ru/innopolis/java/attestation/attestation01/database.txt";
    private List<User> userCache = new ArrayList<>();
    private final TxtDataWriter txtDataWriter = new TxtDataWriter(FILE_NAME);
    private final List<String> logins = new ArrayList<>();

    private UsersRepositoryFileImpl() {
    }

    public static UsersRepositoryFileImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(User user) throws InvalidNameFormatException, LoginAlreadyTakenException {
        if (checkUser(user) && checkUniqueLogin(user.getLogin())) {
            String newUser = readUserMakeLine.map(user);
            userCache.add(user);
            txtDataWriter.log(newUser);
        }
    }

    @Override
    public User findById(String id) throws IOException, InvalidIDException {
        try (BufferedReader br = Files.newBufferedReader(Path.of(FILE_NAME))) {
            for (String line : br.lines().toList()) {
                User user = readLineMakeUser.map(line);
                if (user.getId().equals(id)) {
                    return user;
                }
            }
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            throw new InvalidIDException("Пользователя с заданным идентификатором не существует", e);
        }
    }

    @Override
    public List<User> findAll() {
        try (BufferedReader br = Files.newBufferedReader(Path.of(FILE_NAME))) {
            for (String line : br.lines().toList()) {
                User user = readLineMakeUser.map(line);
                userCache.add(user);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return userCache;
    }

    @Override
    public void update(User user) throws IOException {
        if (userCache.isEmpty()) {
            userCache = findAll();
        }
        try {
            User oldUser = findById(user.getId());
            user.setId(oldUser.getId());
            user.setLt(oldUser.getLt());
            userCache.remove(oldUser);
        } catch (InvalidIDException e) {
            System.out.println("Существующий пользователь не был найден, создан новый пользователь");
        }
        userCache.add(user);
        clearFile(FILE_NAME);
        userCache
                .forEach(userToWrite -> txtDataWriter.log(readUserMakeLine.map(userToWrite)));
    }

    @Override
    public void deleteById(String id) throws IOException, InvalidIDException {
        if (userCache.isEmpty()) {
            userCache = findAll();
        }
        userCache.remove(findById(id));
        clearFile(FILE_NAME);
        userCache
                .forEach(userToWrite -> txtDataWriter.log(readUserMakeLine.map(userToWrite)));
    }

    @Override
    public void deleteAll() {
        userCache.clear();
        clearFile(FILE_NAME);
    }

    private void clearFile(String file) {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print("");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private boolean checkUser(User userForCheck) throws InvalidNameFormatException {
        String noDigitRegex = ".*\\d.*";
        String noJustLettersRegex = "[a-zA-Z]+";
        if (userForCheck.getPatronymic() == null) {
            userForCheck.setPatronymic("NULL");
        }
        if (userForCheck.getPatronymic().isBlank() || userForCheck.getPatronymic().matches(noDigitRegex)) {
            throw new InvalidNameFormatException("отчество может состоять только из букв и не может быть пустой строкой");
        }
        if (userForCheck.getSecondName() == null || userForCheck.getSecondName().isBlank() || userForCheck.getSecondName().matches(noDigitRegex)) {
            throw new InvalidNameFormatException("фамилия может состоять только из букв и не может быть пустой строкой");
        }
        if (userForCheck.getFirstName() == null || userForCheck.getFirstName().isBlank() || userForCheck.getFirstName().matches(noDigitRegex)) {
            throw new InvalidNameFormatException("имя может состоять только из букв и не может быть пустой строкой");
        }
        if (userForCheck.getPassword() == null || userForCheck.getPassword().isBlank() || userForCheck.getPassword().length() > 20 || userForCheck.getPassword().matches(noJustLettersRegex)) {
            throw new InvalidNameFormatException("пароль не может быть пустым, состоять только из букв и быть более 20 символов");
        }
        if (!userForCheck.getConfirmPassword().equals(userForCheck.getPassword())) {
            throw new InvalidNameFormatException("пароли должны совпадать");
        }
        return true;
    }

    private boolean checkUniqueLogin(String login) throws LoginAlreadyTakenException {
        if (logins.isEmpty()) {
            userCache.forEach(user -> logins.add(user.getLogin()));
        }
        if (logins.contains(login)) {
            throw new LoginAlreadyTakenException("Пользователь с таким логином уже существует!");
        } else {
            return true;
        }
    }
}
