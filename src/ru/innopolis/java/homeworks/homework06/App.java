package ru.innopolis.java.homeworks.homework06;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Person("Павел Андреевич", 10_000, 'm'));
        personList.add(new Person("Анна Петровна", 2_000, 'f'));
        personList.add(new Person("Борис", 10, 'm'));
        personList.add(new Person("Женя", 0, 'f'));
        personList.add(new Person("Света", -3, 'f'));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new Product("Хлеб", 40));
        productList.add(new Product("Молоко", 60));
        productList.add(new Product("Торт", 1000));
        productList.add(new Product("Кофе растворимый", 879));
        productList.add(new Product("Масло", 150));
        productList.add(new Product("Мороженое", 200));
        productList.add(new Product("Макароны", 800));

        String checkNameOrEnd;
        String checkProduct = "";

        System.out.println("начинаем покупки: ");
        do {
//            checkNameOrEnd = scanner.nextLine().replaceAll("\\s", "").toLowerCase();
            checkNameOrEnd = scanner.nextLine();
//            checkProduct = scanner.nextLine().replaceAll("\\s", "").toLowerCase();
            if (!checkNameOrEnd.equals("END")) {
                checkProduct = scanner.nextLine();
            }
            for (Person person : personList) {
                if (person.getName().equals(checkNameOrEnd)) {
                    for (Product product : productList) {
                        if (product.getNameOfProduct().equals(checkProduct)) {
                            addinToShopper(person, product);
                            break;
                        }
                    }
                    break;
                }
            }
        } while (!Objects.equals(checkNameOrEnd, "END"));
        for (Person person : personList) {
            System.out.println(person);
        }
    }

    static boolean buyinForMoney(Person person, Product product) {
        int thinnerCash = person.getCash() - product.getPrice();
        if (thinnerCash < 0) {
            return false;
        } else {
            person.setCash(thinnerCash);
            return true;
        }
    }

    static void addinToShopper(Person person, Product product) {
        if (buyinForMoney(person, product)) {
            person.addToShopper(product, person.getShopper());
            System.out.println(person.getName() + (person.getGender() == 'm' ? " купил " : " купила ") + product.getNameOfProduct());
        } else {
            System.out.println(person.getName() + " не может позволить себе " + product.getNameOfProduct());
        }
    }
}
