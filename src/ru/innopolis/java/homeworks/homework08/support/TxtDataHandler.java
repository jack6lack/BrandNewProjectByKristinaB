package ru.innopolis.java.homeworks.homework08.support;

import ru.innopolis.java.homeworks.homework08.ppl.Adults;
import ru.innopolis.java.homeworks.homework08.ppl.Children;
import ru.innopolis.java.homeworks.homework08.ppl.Person;
import ru.innopolis.java.homeworks.homework08.product.Product;
import ru.innopolis.java.homeworks.homework08.product.RegularProduct;

import java.util.ArrayList;

public class TxtDataHandler {

    public static ArrayList<Person> readPersonsMakeList(String line) {
        ArrayList<Person> personArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line);
        while (!sb.isEmpty()) {
            int i = sb.indexOf(";");
            int j = sb.indexOf("=");
            String personData = sb.substring(0, i).trim();
            String personName = personData.substring(0, j).trim();
            double personCash = Double.parseDouble(personData.substring(j + 1).trim());
            Person person = new Children(personName, personCash, 'm', 17);
            personArrayList.add(person);
            sb.delete(0, i + 1);
        }
        return personArrayList;
    }

    public static ArrayList<Product> readProductsMakeList(String line) {
        ArrayList<Product> productArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line);
        while (!sb.isEmpty()) {
            int i = sb.indexOf(";");
            int j = sb.indexOf("=");
            String productData = sb.substring(0, i).trim();
            String productName = productData.substring(0, j).trim();
            double productPrice = Double.parseDouble(productData.substring(j + 1).trim());
            Product product = new RegularProduct(productName, (int) productPrice);
            productArrayList.add(product);
            sb.delete(0, i + 1);
        }
        return productArrayList;
    }

}
