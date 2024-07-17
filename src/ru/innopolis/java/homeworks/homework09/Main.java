package ru.innopolis.java.homeworks.homework09;

import ru.innopolis.java.homeworks.homework08.support.TxtLogger;
import ru.innopolis.java.homeworks.homework09.cars.PerformanceCar;
import ru.innopolis.java.homeworks.homework09.cars.ShowCar;
import ru.innopolis.java.homeworks.homework09.races.CasualRace;
import ru.innopolis.java.homeworks.homework09.races.Race;
import ru.innopolis.java.homeworks.homework09.support.TxtDataHandler;
import ru.innopolis.java.homeworks.homework09.cars.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static final TxtLogger txtLogger = new TxtLogger("report_on_task_9");

    public static void main(String[] args) {
        //main
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

        //add
        List<Car> carList = new ArrayList<>();
        List<Race> raceList = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of("resources/car_race_data.txt"))) {
            String condition = "";
            while (!condition.equals("END")) {
                label:
                for (String line : br.lines().toList()) {
                    switch (line) {
                        case "END":
                            condition = "END";
                            break label;
                        case "CARS":
                            condition = "CARS";
                            continue;
                        case "RACES":
                            condition = "RACES";
                            continue;
                    }
                    if (condition.equals("CARS")) {
                        carList.add(TxtDataHandler.readCarDataMakeCarObject(line));
                    }
                    if (condition.equals("RACES")) {
                        raceList.add(TxtDataHandler.readRaceDataMakeRaceObject(line));
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Race race : raceList) {
            race.setParticipants(carList);
            txtLogger.log(race.toString());
        }
    }
}
