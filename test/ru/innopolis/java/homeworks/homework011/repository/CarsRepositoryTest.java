package ru.innopolis.java.homeworks.homework011.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.innopolis.java.homeworks.homework011.model.Car;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarsRepositoryTest {
    CarsRepository carsRepository;
    Map<String, Car> carMap;

    @BeforeAll
    void bigSetUp() {
        carsRepository = new CarsRepositoryImpl();
    }

    @Test
    @Order(1)
    @DisplayName("создание мапы машин")
    void createCars() {
        carMap = carsRepository.createCars("src/ru/innopolis/java/homeworks/homework011/data/input.txt");
        assertEquals(countLinesNumber(), carMap.size());
    }

    @ParameterizedTest
    @Order(2)
    @DisplayName("получение машин по цвету или пробегу")
    @CsvSource({
            "White, 0",
            "Black, 160000",
    })
    void getCarsByColorOrMileage(String requiredColor, Integer requiredMileage) {
        List<String> carList = carsRepository.getCarsByColorOrMileage(requiredColor, requiredMileage);
        assertEquals(4, carList.size());
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("получение машин в ценовом диапазоне")
    @CsvSource({
            "700000, 800000",
            "800000, 8500000",
    })
    void getCountByPrice(Integer bottomLimit, Integer upperLimit) {
        Integer carsCount = carsRepository.getCountByPrice(bottomLimit, upperLimit);
        assertEquals(4, carsCount);
    }

    @Test
    @Order(4)
    @DisplayName("получение цвета самой бюджетной машины")
    void getMostBudgetModelColor() {
        String mostBudgetCarColor = carsRepository.getMostBudgetModelColor();
        assertEquals("Purple", mostBudgetCarColor);
    }

    @Test
    @Order(5)
    @DisplayName("подсчет средней стоимости модели")
    void getAverageModelPrice() {
        assertAll(
                () -> assertEquals(744200.00, carsRepository.getAverageModelPrice("Toyota")),
                () -> assertEquals(688670.00, carsRepository.getAverageModelPrice("Volga")),
                () -> assertEquals(8300000.00, carsRepository.getAverageModelPrice("Mercedes"))
        );

    }

    private int countLinesNumber() {
        int linesNumber = 0;
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/ru/innopolis/java/homeworks/homework011/data/input.txt"))) {
            br.readLine();
            for (String line : br.lines().toList()) {
                linesNumber++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return linesNumber;
    }
}