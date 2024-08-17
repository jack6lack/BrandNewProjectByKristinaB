package ru.innopolis.java.attestation.attestation01;

import ru.innopolis.java.attestation.attestation01.exceptions.InvalidIDException;
import ru.innopolis.java.attestation.attestation01.exceptions.InvalidNameFormatException;
import ru.innopolis.java.attestation.attestation01.exceptions.LoginAlreadyTakenException;
import ru.innopolis.java.attestation.attestation01.model.HandcraftedUser;
import ru.innopolis.java.attestation.attestation01.model.User;
import ru.innopolis.java.attestation.attestation01.repositories.UsersRepository;
import ru.innopolis.java.attestation.attestation01.repositories.UsersRepositoryImpl;
import ru.innopolis.java.attestation.attestation01.support.DataExtractionUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class App {
    public static void main(String[] args) {

        UsersRepository usersRepository = UsersRepositoryImpl.getInstance();

        //get user list
        List<User> userList = usersRepository.findAll();
        //create user
        try {
            User newUser = new HandcraftedUser("jack", "qwe123", "qwe123", "Лапкин", "Кристина", "Максимовна", 26, true);
            usersRepository.create(newUser);
        } catch (InvalidNameFormatException | LoginAlreadyTakenException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("все пользователи на момент добавления нового: ");
        userList.forEach(System.out::println);

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
        userList.forEach(System.out::println);

        //delete user by id
        try {
            usersRepository.deleteById("5c9d0cef-f5dc-4fec-a0e0-01d70a8739b8");
        } catch (InvalidIDException idErr) {
            System.out.println(idErr.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("все пользователи после удаления одного: ");
        userList.forEach(System.out::println);

        //delete all
//        usersRepository.deleteAll();

        //some other databases
        usersRepository.printBeautifulLines(DataExtractionUtils.findByAge(userList, 125), "src/ru/innopolis/java/attestation/attestation01/findByAge_database.txt");
        usersRepository.printBeautifulLines(DataExtractionUtils.findByIsWorker(userList), "src/ru/innopolis/java/attestation/attestation01/findByIsWorker_database.txt");
        usersRepository.printBeautifulLines(DataExtractionUtils.findByFirstLetterOfSecondName(userList, "С"), "src/ru/innopolis/java/attestation/attestation01/findByFirstLetterOfSecondName_database.txt");
    }
}
