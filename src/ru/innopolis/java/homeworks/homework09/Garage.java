package ru.innopolis.java.homeworks.homework09;

import ru.innopolis.java.homeworks.homework09.cars.Car;

import java.util.ArrayList;
import java.util.Objects;

public class Garage {
    private ArrayList<Car> parkedCars;

    public Garage(ArrayList<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public Garage() {

    }

    public ArrayList<Car> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(ArrayList<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    public void makeCarCooler(Car car, int newHorsepower, int newAcceleration, int newSuspension) {
        car.setHorsepower(newHorsepower);
        car.setAcceleration(newAcceleration);
        car.setSuspension(100 - newSuspension);
    }

    private String getCarNamesOnly() {
        StringBuilder carName = new StringBuilder();
        for (Car car : parkedCars) {
            carName.append(car.getBrand()).append(" ").append(car.getModel()).append("; ");
        }
        return carName.toString();
    }

    @Override
    public String toString() {
        return "машины в гараже: " +
                getCarNamesOnly();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Garage garage)) return false;
        return Objects.equals(parkedCars, garage.parkedCars);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(parkedCars);
    }
}
