package ru.innopolis.java.homeworks.homework06.repository;

import ru.innopolis.java.homeworks.homework06.Person;
import ru.innopolis.java.homeworks.homework06.Product;

import java.util.ArrayList;
import java.util.List;

public class CustomersRepositoryImpl implements CustomersRepository {

    List<Person> personList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();

    @Override
    public List<Person> readPersonsMakeList(String personsInputLine) {
        List<Person> personArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(personsInputLine);
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
            } else if (personData.contains("f")) {
                gender = 'f';
            } else {
                gender = 0;
            }
            personArrayList.add(new Person(personName, personCash, gender));
            sb.delete(0, i + 2);
        }
        this.personList = personArrayList;
        return personArrayList;
    }

    @Override
    public List<Product> readProductsMakeList(String productsInputLine) {
        List<Product> productArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(productsInputLine);
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
        this.productList = productArrayList;
        return productArrayList;
    }

    @Override
    public void validatePersonAndProductAndInvoke(String enteredPersonLine, String enteredProductLine) {
        for (Person person : this.personList) {
            if (person.getName().equals(enteredPersonLine)) {
                for (Product product : this.productList) {
                    if (product.getNameOfProduct().equals(enteredProductLine)) {
                        addinToShopper(person, product);
                        break;
                    }
                }
                break;
            }
        }
    }

    private boolean buyinForMoney(Person person, Product product) {
        int thinnerCash = person.getCash() - product.getPrice();
        if (thinnerCash < 0) {
            return false;
        } else {
            person.setCash(thinnerCash);
            return true;
        }
    }

    private void addinToShopper(Person person, Product product) {
        if (buyinForMoney(person, product)) {
            person.addToShopper(product, person.getShopper());
            System.out.println(person.getName() + (person.getGender() == 'm' ? " купил " : " купила ") + product.getNameOfProduct());
        } else {
            System.out.println(person.getName() + " не может позволить себе " + product.getNameOfProduct());
        }
    }
}
