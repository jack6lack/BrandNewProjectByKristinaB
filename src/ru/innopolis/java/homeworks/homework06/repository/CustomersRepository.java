package ru.innopolis.java.homeworks.homework06.repository;

import ru.innopolis.java.homeworks.homework06.Person;
import ru.innopolis.java.homeworks.homework06.Product;

import java.util.List;

public interface CustomersRepository {
    List<Person> readPersonsMakeList(String personsInputLine);

    List<Product> readProductsMakeList(String productsInputLine);

    void validatePersonAndProductAndInvoke(String enteredPersonLine, String enteredProductLine);
}
