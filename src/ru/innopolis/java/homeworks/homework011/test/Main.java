package ru.innopolis.java.homeworks.homework011.test;

import ru.innopolis.java.homeworks.homework011.model.Car;
import ru.innopolis.java.homeworks.homework011.repository.CarsRepository;
import ru.innopolis.java.homeworks.homework011.repository.CarsRepositoryImpl;
import ru.innopolis.java.homeworks.homework08.support.TxtLogger;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CarsRepository carsRepository = new CarsRepositoryImpl();
        Map<String, Car> carMap = carsRepository.createCars("src/ru/innopolis/java/homeworks/homework011/data/input.txt");

        TxtLogger txtLogger = new TxtLogger("src/ru/innopolis/java/homeworks/homework011/data/output");

        Scanner scanner = new Scanner(System.in);

        txtLogger.log("Автомобили в базе:\n" +
                "\n" +
                "Number Model Color Mileage Cost");
        for (Car car : carMap.values()) {
            txtLogger.log(car.toString());
        }

        //1
        System.out.println("введите цвет: ");
        String requiredColor = scanner.nextLine();
        System.out.println("введите пробег: ");
        Integer requiredMileage = scanner.nextInt();
        txtLogger.log("номера автомобилей по цвету или пробегу: " + carsRepository.getCarsByColorOrMileage(requiredColor, requiredMileage));

        //2 в примере тоже не уникальные если честно(
        System.out.println("введите ценовой диапазон: ");
        Integer bottomLimit = scanner.nextInt();
        Integer upperLimit = scanner.nextInt();
        txtLogger.log("уникальные автомобили: " + carsRepository.getCountByPrice(bottomLimit, upperLimit));

        //3
        txtLogger.log("цвет автомобиля с минимальной стоимостью: " + carsRepository.getMostBudgetModelColor());

        //4
        System.out.println("введите название модели: ");
        String requiredModel = scanner.next();
        txtLogger.log("средняя стоимость модели Toyota: " + carsRepository.getAverageModelPrice(requiredModel));
    }
}
