package ru.innopolis.java.attestation.attestation01;

import ru.innopolis.java.attestation.attestation01.exceptions.InvalidIDException;
import ru.innopolis.java.attestation.attestation01.exceptions.InvalidNameFormatException;
import ru.innopolis.java.attestation.attestation01.exceptions.LoginAlreadyTakenException;
import ru.innopolis.java.attestation.attestation01.model.User;
import ru.innopolis.java.attestation.attestation01.repositories.UsersRepository;
import ru.innopolis.java.attestation.attestation01.repositories.UsersRepositoryFileImpl;
import ru.innopolis.java.attestation.attestation01.support.DataExtractionUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static ru.innopolis.java.attestation.attestation01.support.DataWriterUtils.printBeautifulLines;

public class App {
    public static void main(String[] args) {

        UsersRepository usersRepository = UsersRepositoryFileImpl.getInstance();

        //get user list
        List<User> userCache = usersRepository.findAll();
        //create user
        try {
            User newUser = new User("jack", "qwe123", "qwe123", "Лапкин", "Кристина", "Максимовна", 26, true);
            usersRepository.create(newUser);
        } catch (InvalidNameFormatException | LoginAlreadyTakenException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("все пользователи на момент добавления нового: ");
        userCache.forEach(System.out::println);

        //findById
        try {
            User userToFind = usersRepository.findById("3d95b057-bb2f-4fdd-8395-95b755ef8785");
            System.out.println("found by id: " + userToFind);
        } catch (InvalidIDException idErr) {
            System.out.println(idErr.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //update user
        User updatedUser = new User("1abe5583-bda4-4954-856e-ec7c01960833", LocalDateTime.now(), "notJack", "789_uio", "789_uio", "Нелапкин", "Некристина", "Немаксимовна", 126, false);
        try {
            usersRepository.update(updatedUser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("все пользователи после обновления одного пользователя: ");
        userCache.forEach(System.out::println);

        //delete user by id
        try {
            usersRepository.deleteById("5c9d0cef-f5dc-4fec-a0e0-01d70a8739b8");
        } catch (InvalidIDException idErr) {
            System.out.println(idErr.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("все пользователи после удаления одного: ");
        userCache.forEach(System.out::println);

        //delete all
//        usersRepository.deleteAll();

        //some other databases
        printBeautifulLines(DataExtractionUtils.findByAge(userCache, 125), "src/ru/innopolis/java/attestation/attestation01/findByAge_database.txt");
        printBeautifulLines(DataExtractionUtils.findByIsWorker(userCache), "src/ru/innopolis/java/attestation/attestation01/findByIsWorker_database.txt");
        printBeautifulLines(DataExtractionUtils.findByFirstLetterOfSecondName(userCache, "С"), "src/ru/innopolis/java/attestation/attestation01/findByFirstLetterOfSecondName_database.txt");
    }
}
