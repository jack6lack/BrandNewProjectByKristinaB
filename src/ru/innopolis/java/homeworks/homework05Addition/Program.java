package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Objects;

public record Program(String programName, int rating, int numberOfViewers) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Program program)) return false;
        return rating == program.rating && numberOfViewers == program.numberOfViewers && Objects.equals(programName, program.programName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(programName, rating, numberOfViewers);
    }

    @Override
    public String toString() {
        return "\"" + programName + "\"" +
                ", рейтинг: " + rating +
                ", число зрителей: " + numberOfViewers;
    }
}
