package ru.innopolis.java.homeworks.homework09.cars;

import java.util.Objects;

public class ShowCar extends Car {
    private int stars;

    public ShowCar(String brand, String model, int yearOfManufacture, int horsepower, int acceleration, int durability, int stars) {
        super(brand, model, yearOfManufacture, horsepower, acceleration, durability);
        this.stars = stars;
    }

    public ShowCar() {

    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return super.toString() + ", оценка популярности (по 5-балльной шкале) - " + stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowCar showCar)) return false;
        if (!super.equals(o)) return false;
        return stars == showCar.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }
}
