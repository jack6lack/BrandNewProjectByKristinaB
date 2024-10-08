package ru.innopolis.java.homeworks.homework08;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private Integer cash;
    private Character gender;
    private List<Product> shopper = new ArrayList<>();

    public Person(String name, Integer cash, Character gender) {
//        this.name = name.replaceAll("\\s", "").toLowerCase();
        this.name = name;
        this.cash = cash;
        this.gender = gender;
    }

    public String getName() {
        if (this.name == null) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        return name;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public char getGender() {
        return gender;
    }

    public List<Product> getShopper() {
        return shopper;
    }

    public void addToShopper(Product product, List<Product> shopper) {
        this.shopper = shopper;
        shopper.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return cash == person.cash && gender == person.gender && Objects.equals(name, person.name) && Objects.equals(shopper, person.shopper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cash, gender, shopper);
    }

    @Override
    public String toString() {
        return (!this.shopper.isEmpty() ? this.name + " - " + this.shopper : this.name + " - Ничего не куплено");
    }
}