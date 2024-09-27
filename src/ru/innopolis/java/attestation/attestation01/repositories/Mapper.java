package ru.innopolis.java.attestation.attestation01.repositories;

@FunctionalInterface
public interface Mapper<T, V> {
    V map(T object);
}
