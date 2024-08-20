package ru.innopolis.java.homeworks.homework08;

import ru.innopolis.java.homeworks.homework013.ByCondition;
import ru.innopolis.java.homeworks.homework013.CheckCondition;
import ru.innopolis.java.homeworks.homework08.support.TxtDataHandler;
import ru.innopolis.java.homeworks.homework08.support.TxtLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class App {

    static final TxtLogger txtLogger = new TxtLogger("src/ru/innopolis/java/homeworks/homework08/output");

    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Path.of("src/ru/innopolis/java/homeworks/homework08/input.txt"))) {
            String condition = "";
            while (!condition.equals("END")) {
                int counter = 0;
                for (String line : br.lines().toList()) {
                    condition = line;
                    if (counter == 0) {
                        personList = TxtDataHandler.readPersonsMakeList(line);
                    }
                    if (counter == 1) {
                        productList = TxtDataHandler.readProductsMakeList(line);
                    }
                    if (counter > 1) {
                        if (line.equals("END")) {
                            break;
                        }
                        int i = line.indexOf("-");
                        String customerName = line.substring(0, i);
                        String productName = line.substring(i + 1);
                        for (Person person : personList) {
                            if (person.getName().equals(customerName)) {
                                for (Product product : productList) {
                                    if (product.getNameOfProduct().equals(productName)) {
                                        addinToShopper(person, product);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //люди и покупки
        for (Person person : personList) {
            txtLogger.log(person.toString());
        }
    }

    static boolean buyinForMoney(Person person, Product product) {
        //013
        try {
            ByCondition<Integer> positiveNumber = (x) -> x >= 0;
            person.setCash(CheckCondition.calculateThinnerIntegerCash(person, product, positiveNumber));
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return false;

        //08
//        int thinnerCash = person.getCash() - product.getPrice();
//        if (thinnerCash < 0) {
//            return false;
//        } else {
//            person.setCash(thinnerCash);
//            return true;
//        }
    }

    static void addinToShopper(Person person, Product product) {
        if (buyinForMoney(person, product)) {
            person.addToShopper(product, person.getShopper());
            txtLogger.log(person.getName() + (person.getGender() == 'm' ? " купил " : " купила ") + product.getNameOfProduct());
        } else {
            txtLogger.log(person.getName() + " не может позволить себе " + product.getNameOfProduct());
        }
    }
}
