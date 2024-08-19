package ru.innopolis.java.homeworks.homework011.repository;

import ru.innopolis.java.homeworks.homework011.model.Car;

import java.util.List;
import java.util.Map;

public interface CarsRepository {

    Map<String, Car> createCars(String inputFile);

    List<String> getCarsByColorOrMileage(String colorToFind, Integer mileageToFind);

    Integer getCountByPrice(Integer bottomLimit, Integer upperLimit);

    String getMostBudgetModelColor();

    Double getAverageModelPrice(String modelName);
}
