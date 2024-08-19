package ru.innopolis.java.homeworks.homework08.ppl;

import ru.innopolis.java.homeworks.homework08.product.RegularProduct;

import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String name;
    private double cash;
    private char gender;
    private int age;
    private ArrayList<RegularProduct> shopper = new ArrayList<>();

    public Person(String name, double cash, char gender, int age) {
//        this.name = name.replaceAll("\\s", "").toLowerCase();
        this.name = name;
        this.cash = cash;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        if (this.name.isBlank()) {
            System.out.println("имя не может быть пустым!");
            return null;
        } else {
            return name;
        }
    }

    public double getCash() {
        return this.cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public char getGender() {
        return gender;
    }

    public ArrayList<RegularProduct> getShopper() {
        return shopper;
    }

    public void addToShopper(RegularProduct regularProduct, ArrayList<RegularProduct> shopper) {
        this.shopper = shopper;
        shopper.add(regularProduct);
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
