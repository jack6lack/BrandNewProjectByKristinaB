package ru.innopolis.java.homeworks.homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String persons = scanner.nextLine();
        List<Person> personList = readPersonsMakeList(persons);

        String products = scanner.nextLine();
        List<Product> productList = readProductsMakeList(products);

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

    private static List<Person> readPersonsMakeList(String line) {
        List<Person> personArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line);
        int i, j, k;
        while (!sb.isEmpty()) {
            i = sb.indexOf(";");
            j = sb.indexOf("=");
            k = sb.indexOf(",");
            char gender;
            String personData = sb.substring(0, i).trim();
            String personName = personData.substring(0, j).trim();
            int personCash = Integer.parseInt(personData.substring(j + 1, k).trim());
            if (personData.contains("m")) {
                gender = 'm';
            } else {
                gender = 'f';
            }
            personArrayList.add(new Person(personName, personCash, gender));
            sb.delete(0, i + 2);
        }
        return personArrayList;
    }

    private static List<Product> readProductsMakeList(String line) {
        List<Product> productArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line);
        int i, j;
        while (!sb.isEmpty()) {
            i = sb.indexOf(";");
            j = sb.indexOf("=");
            String productData = sb.substring(0, i).trim();
            String productName = productData.substring(0, j).trim();
            int productPrice = Integer.parseInt(productData.substring(j + 1).trim());
            productArrayList.add(new Product(productName, productPrice));
            sb.delete(0, i + 2);
        }
        return productArrayList;
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