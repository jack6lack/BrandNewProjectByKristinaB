package ru.innopolis.java.attestation.attestation01.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class HandcraftedUser extends User {
    public HandcraftedUser(String login, String password, String confirmPassword, String secondName, String firstName, String patronymic, Integer age, boolean isWorker) {
        super(UUID.randomUUID().toString(), LocalDateTime.now(), login, password, confirmPassword, secondName, firstName, patronymic, age, isWorker);
    }
}
