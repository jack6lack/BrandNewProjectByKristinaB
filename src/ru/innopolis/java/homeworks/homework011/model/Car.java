package ru.innopolis.java.homeworks.homework011.model;

import java.util.Objects;

public class Car {
    private String carNumber;
    private String carModel;
    private String carColor;
    private Integer carMileage;
    private Integer carPrice;

    public Car(String carNumber, String carModel, String carColor, Integer carMileage, Integer carPrice) {
        this.carNumber = carNumber;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carMileage = carMileage;
        this.carPrice = carPrice;
    }

    public Car() {
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Integer getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(Integer carMileage) {
        this.carMileage = carMileage;
    }

    public Integer getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Integer carPrice) {
        this.carPrice = carPrice;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNumber='" + carNumber + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carMileage=" + carMileage +
                ", carPrice=" + carPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return Objects.equals(getCarNumber(), car.getCarNumber()) && Objects.equals(getCarModel(), car.getCarModel()) && Objects.equals(getCarColor(), car.getCarColor()) && Objects.equals(getCarMileage(), car.getCarMileage()) && Objects.equals(getCarPrice(), car.getCarPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarNumber(), getCarModel(), getCarColor(), getCarMileage(), getCarPrice());
    }
}
