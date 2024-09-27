package ru.innopolis.java.homeworks.homework013;

@FunctionalInterface
public interface ByCondition<T extends Number> {

    boolean isOk(T number);
}
