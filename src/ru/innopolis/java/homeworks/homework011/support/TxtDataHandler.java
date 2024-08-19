package ru.innopolis.java.homeworks.homework011.support;

import ru.innopolis.java.homeworks.homework011.model.Car;

public class TxtDataHandler {

    public static Car readCarDataMakeCarObject(String line) {
        String[] carData = line.split("\\|");
        Car car = new Car();
        car.setCarNumber(carData[0]);
        car.setCarModel(carData[1]);
        car.setCarColor(carData[2]);
        car.setCarMileage(Integer.parseInt(carData[3]));
        car.setCarPrice(Integer.parseInt(carData[4]));
        return car;
    }
}
