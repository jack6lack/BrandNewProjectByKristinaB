package ru.innopolis.java.attestation.attestation01.support;

import ru.innopolis.java.attestation.attestation01.model.User;

import java.util.List;
import java.util.Objects;

public class DataExtractionUtils {
    public static List<User> findByAge(List<User> mainUserList, Integer age) {
        return mainUserList.stream()
                .filter(user -> Objects.equals(user.getAge(), age))
                .toList();
    }

    public static List<User> findByIsWorker(List<User> mainUserList) {
        return mainUserList.stream()
                .filter(User::isWorker)
                .toList();
    }

    public static List<User> findByFirstLetterOfSecondName(List<User> mainUserList, String firstLetter) {
        return mainUserList.stream()
                .filter(user -> user.getSecondName().startsWith(firstLetter))
                .toList();
    }
}
