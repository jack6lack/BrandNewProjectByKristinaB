package ru.innopolis.java.homeworks.homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int cash;
    private char gender;
    private List<Product> shopper = new ArrayList<>();

    public Person(String name, int cash, char gender) {
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

    public void setName(String name) {
        this.name = name;
    }

    public int getCash() {
        if (this.cash < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
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