package ru.innopolis.java.homeworks.homework011.test;

import ru.innopolis.java.homeworks.homework011.model.Car;
import ru.innopolis.java.homeworks.homework011.support.TxtDataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Map<String, Car> carMap = new HashMap<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/ru/innopolis/java/homeworks/homework011/data/input.txt"))) {
            br.readLine(); //скип первой линии
            for (String line : br.lines().toList()) {
                Car car = TxtDataHandler.readCarDataMakeCarObject(line);
                carMap.put(car.getCarNumber(), car);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scanner scanner = new Scanner(System.in);

        //1
        System.out.println("введите цвет: ");
        String requiredColor = scanner.nextLine();
        System.out.println("введите пробег: ");
        Integer requiredMileage = scanner.nextInt();
        System.out.println("номера автомобилей по цвету или пробегу: ");
        carMap.values().stream()
                .filter(car -> filterByColorOrMileage(car, requiredColor, requiredMileage))
                .forEach(car -> System.out.println(car.getCarNumber()));

        //2 в примере тоже не уникальные если честно(
        System.out.println("введите ценовой диапазон: ");
        Integer bottomLimit = scanner.nextInt();
        Integer upperLimit = scanner.nextInt();
        Long uniqueCars = carMap.values().stream()
                .filter(car -> isFitsIntoBudget(car, bottomLimit, upperLimit)).count();
        System.out.println("уникальные автомобили: " + uniqueCars);

        //3
        System.out.println("цвет автомобиля с минимальной стоимостью: " + carMap.values().stream()
                .min(Comparator.comparingInt(Car::getCarPrice))
                .get().getCarColor());

        //4
        System.out.println("введите название модели: ");
        String requiredModel = scanner.next();
        Double modelToFind = carMap.values().stream()
                .filter(car -> car.getCarModel().equals(requiredModel))
                .mapToInt(Car::getCarPrice)
                .average()
                .getAsDouble();

        System.out.println("средняя стоимость модели Toyota: " + modelToFind);
    }

    private static boolean isFitsIntoBudget(Car car, Integer bottomLimit, Integer upperLimit) {
        return car.getCarPrice() > bottomLimit && car.getCarPrice() < upperLimit;
    }

    private static boolean filterByColorOrMileage(Car car, String requiredColor, Integer requiredMileage) {
        return car.getCarColor().equals(requiredColor) || car.getCarMileage().equals(requiredMileage);
    }
}
