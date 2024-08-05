package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Objects;

public record Channel(String channelName, int number, Program program) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Channel channel)) return false;
        return number == channel.number && Objects.equals(program, channel.program) && Objects.equals(channelName, channel.channelName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelName, number, program);
    }

    @Override
    public String toString() {
        return channelName + ", номер " + number + ", сейчас идет: " + program;
    }
}
