package ru.innopolis.java.homeworks.homework06;

import ru.innopolis.java.homeworks.homework06.repository.CustomersRepository;
import ru.innopolis.java.homeworks.homework06.repository.CustomersRepositoryImpl;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CustomersRepository customersRepository = new CustomersRepositoryImpl();

        String persons = scanner.nextLine();
        List<Person> personList = customersRepository.readPersonsMakeList(persons);

        String products = scanner.nextLine();
        customersRepository.readProductsMakeList(products);

        String checkNameOrEnd;
        String checkProduct;

        System.out.println("начинаем покупки: ");
        do {
            checkNameOrEnd = scanner.nextLine();
            if (checkNameOrEnd.equals("END")) {
                break;
            }
            checkProduct = scanner.nextLine();
            customersRepository.validatePersonAndProductAndInvoke(checkNameOrEnd, checkProduct);

        } while (!Objects.equals(checkNameOrEnd, "END"));
        for (Person person : personList) {
            System.out.println(person);
        }
    }
}