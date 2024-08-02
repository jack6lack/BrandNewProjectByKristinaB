package ru.innopolis.java.homeworks.homework011.repository;

import ru.innopolis.java.homeworks.homework011.model.Car;
import ru.innopolis.java.homeworks.homework011.support.TxtDataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CarsRepositoryImpl implements CarsRepository {

    private Map<String, Car> carMap = new HashMap<>();

    @Override
    public Map<String, Car> createCars(String inputFile) {
        try (BufferedReader br = Files.newBufferedReader(Path.of(inputFile))) {
            br.readLine(); //скип первой линии
            for (String line : br.lines().toList()) {
                Car car = TxtDataHandler.readCarDataMakeCarObject(line);
                carMap.put(car.getCarNumber(), car);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return carMap;
    }

    @Override
    public List<String> getCarsByColorOrMileage(String colorToFind, Integer mileageToFind) {
        return carMap.values().stream()
                .filter(car -> filterByColorOrMileage(car, colorToFind, mileageToFind))
                .map(Car::getCarNumber)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getCountByPrice(Integer bottomLimit, Integer upperLimit) {
        Long uniqueCars = carMap.values().stream()
                .filter(car -> isFitsIntoBudget(car, bottomLimit, upperLimit)).count();
        return Math.toIntExact(uniqueCars);
    }

    @Override
    public String getMostBudgetModelColor() {
        return carMap.values().stream()
                .min(Comparator.comparingInt(Car::getCarPrice))
                .get().getCarColor();
    }

    @Override
    public Double getAverageModelPrice(String modelName) {
        return carMap.values().stream()
                .filter(car -> car.getCarModel().equals(modelName))
                .mapToInt(Car::getCarPrice)
                .average()
                .getAsDouble();
    }

    private static boolean filterByColorOrMileage(Car car, String requiredColor, Integer requiredMileage) {
        return car.getCarColor().equals(requiredColor) || car.getCarMileage().equals(requiredMileage);
    }

    private static boolean isFitsIntoBudget(Car car, Integer bottomLimit, Integer upperLimit) {
        return car.getCarPrice() > bottomLimit && car.getCarPrice() < upperLimit;
    }
}
