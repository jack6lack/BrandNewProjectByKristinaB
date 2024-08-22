package ru.innopolis.java.homeworks.homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private int cash;
    private char gender;
    private List<Product> shopper = new ArrayList<>();

    public Person() {
    }

    public Person(String name, int cash, char gender) {
//        this.name = name.replaceAll("\\s", "").toLowerCase();
        setName(name);
        setCash(cash);
        setGender(gender);
    }

    public String getName() {
        if (!isMatchingNamingRule(this.name)) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        return name;
    }

    public void setName(String name) {
        if (!isMatchingNamingRule(name)) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        this.name = name;
    }

    public int getCash() {
        if (!isMatchingCashRule(this.cash)) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        return cash;
    }

    public void setCash(int cash) {
        if (!isMatchingCashRule(cash)) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.cash = cash;
    }

    public char getGender() {
        if (!isMatchingGenderRule(this.gender)) {
            throw new IllegalArgumentException("вы не можете быть вертолетом апач");
        }
        return gender;
    }

    public void setGender(char gender) {
        if (!isMatchingGenderRule(gender)) {
            throw new IllegalArgumentException("вы не можете быть вертолетом апач");
        }
        this.gender = gender;
    }

    public List<Product> getShopper() {
        return shopper;
    }

    public void setShopper(List<Product> shopper) {
        this.shopper = shopper;
    }

    public void addToShopper(Product product, List<Product> shopper) {
        this.shopper = shopper;
        shopper.add(product);
    }

    private boolean isMatchingNamingRule(String string) {
        return string != null && !string.isBlank();
    }

    private boolean isMatchingGenderRule(char gender) {
        return gender == 'f' || gender == 'm';
    }

    private boolean isMatchingCashRule(int cash) {
        return cash >= 0;
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