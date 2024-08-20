package ru.innopolis.java.homeworks.homework08.support;


import ru.innopolis.java.homeworks.homework013.utils.InputParser;
import ru.innopolis.java.homeworks.homework08.Person;
import ru.innopolis.java.homeworks.homework08.Product;

import java.util.ArrayList;
import java.util.List;

public class TxtDataHandler {

    private TxtDataHandler() {
    }

    public static List<Person> readPersonsMakeList(String line) {
        List<Person> personArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line);
        while (!sb.isEmpty()) {
            Integer i = sb.indexOf(";");
            Integer j = sb.indexOf("=");
            String personData = sb.substring(0, i).trim();
            String personName = personData.substring(0, j).trim();
            Integer personCash = InputParser.validateCount.map(personData.substring(j + 1).trim());
            Person person = new Person(personName, personCash, 'm');
            personArrayList.add(person);
            sb.delete(0, i + 1);
        }
        return personArrayList;
    }

    public static List<Product> readProductsMakeList(String line) {
        List<Product> productArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line);
        while (!sb.isEmpty()) {
            Integer i = sb.indexOf(";");
            Integer j = sb.indexOf("=");
            String productData = sb.substring(0, i).trim();
            String productName = productData.substring(0, j).trim();
            Integer productPrice = Integer.parseInt(productData.substring(j + 1).trim());
            Product product = new Product(productName, productPrice);
            productArrayList.add(product);
            sb.delete(0, i + 1);
        }
        return productArrayList;
    }

}
