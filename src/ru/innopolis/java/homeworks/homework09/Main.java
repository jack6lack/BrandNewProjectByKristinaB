package ru.innopolis.java.homeworks.homework09;

import ru.innopolis.java.homeworks.homework09.cars.Car;
import ru.innopolis.java.homeworks.homework09.cars.PerformanceCar;
import ru.innopolis.java.homeworks.homework09.cars.ShowCar;
import ru.innopolis.java.homeworks.homework09.races.CasualRace;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] car1Mods = {"модернизация подвески", "модернизация тормозов", "турбонадув"};
        Car car1 = new PerformanceCar("toyota", "celica", 2003, 143, 4, 25, car1Mods);
        System.out.println(car1);
        ShowCar car2 = new ShowCar("toyota", "rav4", 1999, 135, 3, 50, 10);
        System.out.println(car2);
        String[] car3Mods = {"легкая модернизация колес и шин", "модернизация подвески", "турбонадув"};
        PerformanceCar car3 = new PerformanceCar("nissan", "tiida", 2008, 110, 2, 50, car3Mods);
        System.out.println(car3);
        ArrayList<Car> carArrayList = new ArrayList<>();
        carArrayList.add(car1);
        carArrayList.add(car2);
        carArrayList.add(car3);
        Garage garage = new Garage(carArrayList);
        System.out.println(garage);
        garage.makeCarCooler(car3, 200, 5, 25);
        System.out.println("апгрейд: " + car3);
        CasualRace cr = new CasualRace(3955, "moscow raceway", 1_000_000, carArrayList);
        System.out.println(cr);

    }
}
