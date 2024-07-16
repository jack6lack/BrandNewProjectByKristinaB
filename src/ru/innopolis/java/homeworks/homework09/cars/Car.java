package ru.innopolis.java.homeworks.homework09.cars;

import java.util.Objects;

public class Car implements ICar {

    private String brand;
    private String model;
    private int yearOfManufacture;
    private int horsepower;
    private int acceleration;
    private int suspension = 100;
    private int durability;

    protected Car() {

    }

    protected Car(String brand, String model, int yearOfManufacture, int horsepower, int acceleration, int durability) {
        this.brand = brand;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.durability = durability;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getSuspension() {
        return suspension;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public String toString() {
        return "характеристики авто : " +
                "марка - '" + brand + '\'' +
                ", модель - '" + model + '\'' +
                ", год выпуска - " + yearOfManufacture +
                ", мощность - " + horsepower + " (л/с)" +
                ", ускорение - " + acceleration + " м/с^2" +
                ", высота подвески (относительно стоковой) - " + suspension + "%" +
                ", долговечность - " + durability + " лет";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return yearOfManufacture == car.yearOfManufacture && horsepower == car.horsepower && acceleration == car.acceleration && suspension == car.suspension && durability == car.durability && Objects.equals(brand, car.brand) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, yearOfManufacture, horsepower, acceleration, suspension, durability);
    }
}
