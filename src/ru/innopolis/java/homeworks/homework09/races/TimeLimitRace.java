package ru.innopolis.java.homeworks.homework09.races;

import ru.innopolis.java.homeworks.homework09.cars.Car;

import java.util.ArrayList;
import java.util.Objects;

public class TimeLimitRace extends Race {
    private int goldTime;

    public TimeLimitRace(int routeLength, String routeName, int prizeFund, ArrayList<Car> participants, int goldTime) {
        super(routeLength, routeName, prizeFund, participants);
        this.goldTime = goldTime;
    }

    public TimeLimitRace() {
    }

    public int getGoldTime() {
        return goldTime;
    }

    public void setGoldTime(int goldTime) {
        this.goldTime = goldTime;
    }

    @Override
    public String toString() {
        return super.toString() + ", лучшее время - " + goldTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeLimitRace that)) return false;
        if (!super.equals(o)) return false;
        return getGoldTime() == that.getGoldTime();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getGoldTime());
    }
}
