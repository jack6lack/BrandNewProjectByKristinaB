package ru.innopolis.java.homeworks.homework09.races;

import ru.innopolis.java.homeworks.homework09.cars.Car;

import java.util.List;
import java.util.Objects;

public class Race implements IRace {
    private int routeLength;
    private String routeName;
    private int prizeFund;
    private List<Car> participants;

    protected Race(int routeLength, String routeName, int prizeFund, List<Car> participants) {
        this.routeLength = routeLength;
        this.routeName = routeName;
        this.prizeFund = prizeFund;
        this.participants = participants;
    }

    protected Race() {

    }

    public int getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(int routeLength) {
        this.routeLength = routeLength;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public int getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(int prizeFund) {
        this.prizeFund = prizeFund;
    }

    public List<Car> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Car> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "гонка: " +
                "'" + routeName + '\'' +
                ", протяженность трассы - " + routeLength + " км" +
                ", призовой фонд - " + prizeFund + " ptas" +
                ", участники - " + participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Race race)) return false;
        return routeLength == race.routeLength && prizeFund == race.prizeFund && Objects.equals(routeName, race.routeName) && Objects.equals(participants, race.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeLength, routeName, prizeFund, participants);
    }
}
