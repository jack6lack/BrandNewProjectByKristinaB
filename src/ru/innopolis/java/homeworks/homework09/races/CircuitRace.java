package ru.innopolis.java.homeworks.homework09.races;

import ru.innopolis.java.homeworks.homework09.cars.Car;

import java.util.ArrayList;
import java.util.Objects;

public class CircuitRace extends Race {
    private int laps;

    public CircuitRace(int routeLength, String routeName, int prizeFund, ArrayList<Car> participants, int laps) {
        super(routeLength, routeName, prizeFund, participants);
        this.laps = laps;
    }

    public CircuitRace() {
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    @Override
    public String toString() {
        return super.toString() + ", количество кругов - " + laps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CircuitRace that)) return false;
        if (!super.equals(o)) return false;
        return getLaps() == that.getLaps();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLaps());
    }
}
