package ru.innopolis.java.homeworks.homework10.add;

import java.util.Objects;

public class Pair<T, V> {
    private final T first;
    private final V second;

    private Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public V getSecond() {
        return this.second;
    }

    public static <T, V> Pair<T, V> of(T first, V second) {
        return new Pair<>(first, second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?> pair)) return false;
        return Objects.equals(getFirst(), pair.getFirst()) && Objects.equals(getSecond(), pair.getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond());
    }
}
