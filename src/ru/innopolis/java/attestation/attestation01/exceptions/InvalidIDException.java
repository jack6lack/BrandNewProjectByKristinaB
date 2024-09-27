package ru.innopolis.java.attestation.attestation01.exceptions;

public class InvalidIDException extends Exception {
    public InvalidIDException(String errorMassage, Throwable cause) {
        super(errorMassage, cause);
    }
}
