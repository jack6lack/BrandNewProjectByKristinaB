package ru.innopolis.java.homeworks.homework09.support;

import ru.innopolis.java.homeworks.homework09.cars.Car;
import ru.innopolis.java.homeworks.homework09.cars.PerformanceCar;
import ru.innopolis.java.homeworks.homework09.cars.ShowCar;
import ru.innopolis.java.homeworks.homework09.races.CasualRace;
import ru.innopolis.java.homeworks.homework09.races.CircuitRace;
import ru.innopolis.java.homeworks.homework09.races.Race;
import ru.innopolis.java.homeworks.homework09.races.TimeLimitRace;

public class TxtDataHandler {

    public static Car readCarDataMakeCarObject(String line) {
        String[] carData = line.split(",");
        if (carData[7].startsWith(" <")) {
            String[] carAddons = carData[7].trim().replaceAll("[<>]", "").split(";");
            PerformanceCar performanceCar = new PerformanceCar();
            performanceCar.setBrand(carData[0]);
            performanceCar.setModel(carData[1]);
            performanceCar.setYearOfManufacture(Integer.parseInt(carData[2].trim()));
            performanceCar.setHorsepower(Integer.parseInt(carData[3].trim()));
            performanceCar.setAcceleration(Integer.parseInt(carData[4].trim()));
            performanceCar.setSuspension(Integer.parseInt(carData[5].trim()));
            performanceCar.setDurability(Integer.parseInt(carData[6].trim()));
            performanceCar.setAddOns(carAddons);
            return performanceCar;
        } else {
            ShowCar showCar = new ShowCar();
            showCar.setBrand(carData[0]);
            showCar.setModel(carData[1]);
            showCar.setYearOfManufacture(Integer.parseInt(carData[2].trim()));
            showCar.setHorsepower(Integer.parseInt(carData[3].trim()));
            showCar.setAcceleration(Integer.parseInt(carData[4].trim()));
            showCar.setSuspension(Integer.parseInt(carData[5].trim()));
            showCar.setDurability(Integer.parseInt(carData[6].trim()));
            showCar.setStars(Integer.parseInt(carData[7].trim()));
            return showCar;
        }
    }

    public static Race readRaceDataMakeRaceObject(String line) {
        String[] raceData = line.split(",");
        if (raceData.length == 3) {
            CasualRace casualRace = new CasualRace();
            casualRace.setRouteName(raceData[1].trim());
            casualRace.setRouteLength(Integer.parseInt(raceData[0].trim()));
            casualRace.setPrizeFund(Integer.parseInt(raceData[2].trim()));
            return casualRace;
        } else if (raceData[3].endsWith("l")) {
            CircuitRace circuitRace = new CircuitRace();
            circuitRace.setRouteName(raceData[1].trim());
            circuitRace.setRouteLength(Integer.parseInt(raceData[0].trim()));
            circuitRace.setPrizeFund(Integer.parseInt(raceData[2].trim()));
            circuitRace.setLaps(Integer.parseInt(raceData[3].replace("l", "").trim()));
            return circuitRace;
        } else {
            TimeLimitRace timeLimitRace = new TimeLimitRace();
            timeLimitRace.setRouteName(raceData[1].trim());
            timeLimitRace.setRouteLength(Integer.parseInt(raceData[0].trim()));
            timeLimitRace.setPrizeFund(Integer.parseInt(raceData[2].trim()));
            timeLimitRace.setGoldTime(Integer.parseInt(raceData[3].replace("t", "").trim()));
            return timeLimitRace;
        }
    }

}
