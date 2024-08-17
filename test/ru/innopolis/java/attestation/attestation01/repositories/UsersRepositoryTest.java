package ru.innopolis.java.attestation.attestation01.repositories;

import org.junit.jupiter.api.*;
import ru.innopolis.java.attestation.attestation01.exceptions.InvalidIDException;
import ru.innopolis.java.attestation.attestation01.exceptions.InvalidNameFormatException;
import ru.innopolis.java.attestation.attestation01.exceptions.LoginAlreadyTakenException;
import ru.innopolis.java.attestation.attestation01.model.HandcraftedUser;
import ru.innopolis.java.attestation.attestation01.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsersRepositoryTest {
    private UsersRepository usersRepository;
    private List<User> userList = new ArrayList<>();
    private User tempUser;

    @BeforeAll
    void bigSetUp() {
        usersRepository = UsersRepositoryImpl.getInstance();
        userList = usersRepository.findAll();
    }

    @AfterAll
    void bigTearDown() {
        userList.clear();
    }

    @Test
    @Order(1)
    @DisplayName("добавление пользователя с уже существующим логином")
    void createAlreadyExistedUser() {
        int originalListSize = userList.size();
        int linesNumber = countLinesNumber();
        try {
            User newUser = new HandcraftedUser("jack", "qwe123", "qwe123", "Лапкин", "Кристина", "Максимовна", 26, true);
            usersRepository.create(newUser);
        } catch (InvalidNameFormatException | LoginAlreadyTakenException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(originalListSize, userList.size());
        assertEquals(linesNumber, countLinesNumber());
    }

    @Test
    @Order(2)
    @DisplayName("добавление гарантированно нового пользователя")
    void createGuaranteedNewUser() throws InvalidIDException, IOException {
        int originalListSize = userList.size();
        int linesNumber = countLinesNumber();
        try {
            User newUser = new HandcraftedUser(STR."jack\{Math.random()}", "qwe123", "qwe123", "Лапкин", "Кристина", "Максимовна", 26, true);
            usersRepository.create(newUser);
            tempUser = newUser;
        } catch (InvalidNameFormatException | LoginAlreadyTakenException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(originalListSize + 1, userList.size());
        assertEquals(linesNumber + 1, countLinesNumber());
        usersRepository.deleteById(tempUser.getId());
    }

    @Test
    @Order(3)
    @DisplayName("добавление пользователя с некорректными данными")
    void createUserWithIncorrectData() {
        User notMatchingNamingRulesUser = new HandcraftedUser("jack1", "123qwe", "123qwe", "Лапкин5", "Кри5стина", "Макс5имовна", 26, true);
        User notMatchingUniqueIDRuleUser = new HandcraftedUser("jack", "123qwe", "123qwe", "Лапкин", "Кристина", "Максимовна", 26, true);
        assertAll(
                () -> assertThrows(InvalidNameFormatException.class, () -> usersRepository.create(notMatchingNamingRulesUser)),
                () -> assertThrows(LoginAlreadyTakenException.class, () -> usersRepository.create(notMatchingUniqueIDRuleUser))
        );
    }

    @Test
    @Order(4)
    @DisplayName("поиск гарантированно существующего пользователя")
    void findByExistedId() {
        String existedId = "1abe5583-bda4-4954-856e-ec7c01960833";
        assertDoesNotThrow(() -> usersRepository.findById(existedId));
    }

    @Test
    @Order(5)
    @DisplayName("поиск гарантированно несуществующего пользователя")
    void findByNonExistedId() {
        String existedId = "1abe5583-bda4-4954-856e-ec7c01960813";
        assertThrows(InvalidIDException.class, () -> usersRepository.findById(existedId));
    }

    @Test
    @Order(6)
    @DisplayName("размер листа точно равен количеству строк в файле")
    void findAll() {
        assertEquals(userList.size(), countLinesNumber());
    }

    @Test
    @Order(7)
    @DisplayName("обновление данных существующему пользователю")
    void updateExistedUser() {
        int originalListSize = userList.size();
        int linesNumber = countLinesNumber();
        tempUser = new User("1abe5583-bda4-4954-856e-ec7c01960833", LocalDateTime.now(), "notJack", "789_uio", "789_uio", "Нелапкин", "Некристина", "Немаксимовна", 126, false);
        try {
            usersRepository.update(tempUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(originalListSize, userList.size());
        assertEquals(linesNumber, countLinesNumber());
    }

    @Test
    @Order(8)
    @DisplayName("обновление данных несуществующему пользователю (создание нового)")
    void updateNonExistedUser() {
        int originalListSize = userList.size();
        int linesNumber = countLinesNumber();
        tempUser = new User("1abe5583-bdb4-4954-856e-ec7c01960833", LocalDateTime.now(), "notJack1", "789_uio", "789_uio", "Нелапкин", "Некристина", "Немаксимовна", 126, false);
        try {
            usersRepository.update(tempUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(originalListSize + 1, userList.size());
        assertEquals(linesNumber + 1, countLinesNumber());
    }

    @Test
    @Order(9)
    @DisplayName("удаление существующего пользователя")
    void deleteExistedUserById() {
        int originalListSize = userList.size();
        int linesNumber = countLinesNumber();
        try {
            usersRepository.deleteById("1abe5583-bdb4-4954-856e-ec7c01960833");
        } catch (InvalidIDException idErr) {
            System.out.println(idErr.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(originalListSize - 1, userList.size());
        assertEquals(linesNumber - 1, countLinesNumber());
    }

    @Test
    @Order(10)
    @DisplayName("удаление несуществующего пользователя")
    void deleteNonExistedUserById() {
        int originalListSize = userList.size();
        int linesNumber = countLinesNumber();
        try {
            usersRepository.deleteById("1abe5583-bdb4-4054-856e-ec7c01960833");
        } catch (InvalidIDException idErr) {
            System.out.println(idErr.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertEquals(originalListSize, userList.size());
        assertEquals(linesNumber, countLinesNumber());
    }

    @Test
    @Disabled
    @Order(11)
    @DisplayName("удаление всех пользователей из файла и листа")
    void deleteAll() {
        usersRepository.deleteAll();
        assertEquals(0, userList.size());
        assertEquals(0, countLinesNumber());
    }

    private int countLinesNumber() {
        int linesNumber = 0;
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/ru/innopolis/java/attestation/attestation01/database.txt"))) {
            for (String line : br.lines().toList()) {
                linesNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return linesNumber;
    }
}