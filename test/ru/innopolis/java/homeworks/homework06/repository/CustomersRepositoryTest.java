package ru.innopolis.java.homeworks.homework06.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.innopolis.java.homeworks.homework06.Person;
import ru.innopolis.java.homeworks.homework06.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomersRepositoryTest {
    CustomersRepository customersRepository;
    List<Person> personList;
    List<Product> productList;

    @BeforeAll
    void bigSetUp() {
        customersRepository = new CustomersRepositoryImpl();
        personList = new ArrayList<>();
        productList = new ArrayList<>();
    }

    @ParameterizedTest
    @Order(1)
    @DisplayName("добавление людей в массив")
    @ValueSource(strings = "Павел Андреевич = 10000, m; Анна Петровна = 2000, f; Борис = 100, m;")
    void readPersonsMakeList(String persons) {
        personList = customersRepository.readPersonsMakeList(persons);
        assertEquals(3, personList.size());
    }

    @ParameterizedTest
    @Order(2)
    @DisplayName("добавление продуктов в массив")
    @ValueSource(strings = "Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150;")
    void readProductsMakeList(String products) {
        productList = customersRepository.readProductsMakeList(products);
        assertEquals(5, productList.size());
    }

    @ParameterizedTest
    @Order(3)
    @DisplayName("добавление покупок")
    @CsvSource({
            "Павел Андреевич, Торт",
            "Анна Петровна, Молоко",
            "Борис, Хлеб"
    })
    void validatePersonAndProductAndInvoke(String checkNameOrEnd, String checkProduct) {
        customersRepository.validatePersonAndProductAndInvoke(checkNameOrEnd, checkProduct);
        assertEquals(1, personList.stream()
                .filter(person -> person.getName().equals(checkNameOrEnd))
                .findFirst()
                .get()
                .getShopper()
                .size());

    }

    @ParameterizedTest
    @Order(4)
    @DisplayName("подстановка некорректных данных пользователям")
    @ValueSource(strings = {"Павел Андреевич = -2, m;", " = 10000, m;", "Павел Андреевич = 10000, a;"})
    void tryingToSetIncorrectDataToUser(String inputData) {
        assertThrows(IllegalArgumentException.class, () -> customersRepository.readPersonsMakeList(inputData));
    }

    @Test
    @Order(5)
    @DisplayName("создание корректного пользователя")
    void createCoolUser() {
        assertDoesNotThrow(() -> new Person("super_boris", 500, 'm'));
    }

    @Test
    @Order(6)
    @DisplayName("создание некорректного пользователя")
    void createNotCoolUser() {
        assertThrows(IllegalArgumentException.class, () -> new Person(" ", -2, 'y'));
    }

    @Test
    @Order(7)
    @DisplayName("получение данных корректного пользователя")
    void getCoolUser() {
        Person person = new Person("super_boris", 500, 'm');
        assertAll(
                () -> assertDoesNotThrow(person::getName),
                () -> assertDoesNotThrow(person::getCash),
                () -> assertDoesNotThrow(person::getGender)
        );
    }

    @Test
    @Order(8)
    @DisplayName("успешная покупка")
    void successfulShopping() {
        Person person = personList.getFirst();
        person.setShopper(new ArrayList<>());
        customersRepository.validatePersonAndProductAndInvoke(person.getName(), "Торт");
        assertEquals(1, person.getShopper().size());
    }

    @Test
    @Order(9)
    @DisplayName("неуспешная покупка")
    void unsuccessfulShopping() {
        Person person = personList.getLast();
        person.setShopper(new ArrayList<>());
        customersRepository.validatePersonAndProductAndInvoke(person.getName(), "Торт");
        assertEquals(0, person.getShopper().size());
    }
}