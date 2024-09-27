package ru.innopolis.java.homeworks.homework013.utils;

@FunctionalInterface
public interface Mapper<T, V> {
    V map(T object);
}
