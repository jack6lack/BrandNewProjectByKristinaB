package ru.innopolis.java.attestation.attestation01.repositories;

import ru.innopolis.java.attestation.attestation01.exceptions.InvalidIDException;
import ru.innopolis.java.attestation.attestation01.exceptions.InvalidNameFormatException;
import ru.innopolis.java.attestation.attestation01.exceptions.LoginAlreadyTakenException;
import ru.innopolis.java.attestation.attestation01.model.User;
import ru.innopolis.java.attestation.attestation01.support.TxtLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryImpl implements UsersRepository {

    private static final UsersRepositoryImpl INSTANCE = new UsersRepositoryImpl();

    private static final String FILE_NAME = "src/ru/innopolis/java/attestation/attestation01/database.txt";
    private List<User> userList = new ArrayList<>();
    private final TxtLogger txtLogger = new TxtLogger(FILE_NAME);
    private List<String> logins = new ArrayList<>();

    private UsersRepositoryImpl() {
    }

    public static UsersRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public void create(User user) throws InvalidNameFormatException, LoginAlreadyTakenException {
        if (checkUser(user) && checkUniqueLogin(user.getLogin())) {
            String newUser = readUserMakeLine.map(user);
            userList.add(user);
            txtLogger.log(newUser);
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
                userList.add(user);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return userList;
    }

    @Override
    public void update(User user) throws IOException {
        if (userList.isEmpty()) {
            userList = findAll();
        }
        try {
            User oldUser = findById(user.getId());
            user.setId(oldUser.getId());
            user.setLt(oldUser.getLt());
            userList.remove(oldUser);
        } catch (InvalidIDException e) {
            System.out.println("Существующий пользователь не был найден, создан новый пользователь");
        }
        userList.add(user);
        clearFile(FILE_NAME);
        userList
                .forEach(userToWrite -> txtLogger.log(readUserMakeLine.map(userToWrite)));
    }

    @Override
    public void deleteById(String id) throws IOException, InvalidIDException {
        if (userList.isEmpty()) {
            userList = findAll();
        }
        userList.remove(findById(id));
        clearFile(FILE_NAME);
        userList
                .forEach(userToWrite -> txtLogger.log(readUserMakeLine.map(userToWrite)));
    }

    @Override
    public void deleteAll() {
        userList.clear();
        clearFile(FILE_NAME);
    }

    private Mapper<String, User> readLineMakeUser = line -> {
        User user = new User();
        String[] userData = line.split("\\|");
        user.setId(userData[0]);
        user.setLt(LocalDateTime.parse(userData[1]));
        user.setLogin(userData[2]);
        user.setPassword(userData[3]);
        user.setConfirmPassword(userData[4]);
        user.setSecondName(userData[5]);
        user.setFirstName(userData[6]);
        if (userData[7].equals("NULL")) {
            user.setPatronymic(null);
        } else {
            user.setPatronymic(userData[7]);
        }
        if (userData[8].equals("NULL")) {
            user.setAge(null);
        } else {
            user.setAge(Integer.parseInt(userData[8]));
        }
        user.setWorker(Boolean.parseBoolean(userData[9]));

        return user;
    };

    public void printBeautifulLines(List<User> list, String fileName) {
        TxtLogger logger = new TxtLogger(fileName);
        for (User user : list) {
            logger.log(readUserMakeLine.map(user));
        }
    }

    private Mapper<User, String> readUserMakeLine = user -> {
        StringBuilder sb = new StringBuilder();
        sb.append(user.getId())
                .append("|")
                .append(user.getLt())
                .append("|")
                .append(user.getLogin())
                .append("|")
                .append(user.getPassword())
                .append("|")
                .append(user.getConfirmPassword())
                .append("|")
                .append(user.getSecondName())
                .append("|")
                .append(user.getFirstName())
                .append("|");
        if (user.getPatronymic() == null) {
            sb.append("NULL")
                    .append("|");
        } else {
            sb.append(user.getPatronymic())
                    .append("|");
        }
        if (user.getAge() == null) {
            sb.append("NULL")
                    .append("|");
        } else {
            sb.append(user.getAge())
                    .append("|");
        }
        sb.append(user.isWorker());

        return sb.toString();
    };

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
            userList.forEach(user -> logins.add(user.getLogin()));
        }
        if (logins.contains(login)) {
            throw new LoginAlreadyTakenException("Пользователь с таким логином уже существует!");
        } else {
            return true;
        }
    }
}
