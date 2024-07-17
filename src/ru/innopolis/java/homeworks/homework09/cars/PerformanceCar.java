package ru.innopolis.java.homeworks.homework09.cars;

import java.util.Arrays;
import java.util.Objects;

public class PerformanceCar extends Car {
    private String[] addOns;

    public PerformanceCar(String brand, String model, int yearOfManufacture, int horsepower, int acceleration, int durability, String[] addOns) {
        super(brand, model, yearOfManufacture, (int) Math.round(horsepower * 1.5), acceleration, durability);
        setSuspension(getSuspension() - 25);
        this.addOns = addOns;
    }

    public PerformanceCar() {
    }

    @Override
    public void setSuspension(int suspension) {
        super.setSuspension(suspension - 25);
    }

    @Override
    public void setHorsepower(int horsepower) {
        super.setHorsepower((int) (horsepower * 1.5));
    }

    public String[] getAddOns() {
        return addOns;
    }

    public void setAddOns(String[] addOns) {
        this.addOns = addOns;
    }

    @Override
    public String toString() {
        return super.toString() + ", установленные модификации - " + Arrays.toString(addOns);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PerformanceCar that)) return false;
        return Objects.deepEquals(addOns, that.addOns);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(addOns);
    }
}
