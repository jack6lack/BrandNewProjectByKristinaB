package ru.innopolis.java.attestation.attestation01.exceptions;

public class LoginAlreadyTakenException extends Exception {
    public LoginAlreadyTakenException(String errorMassage) {
        super(errorMassage);
    }
}
