package ru.innopolis.java.homeworks.homework06;

import java.util.Arrays;
import java.util.Objects;

public class Person {
    private String name;
    private int cash;
    private Product[] shopper;

    public Person(String name, int cash, Product[] shopper) {
        this.name = name;
        this.cash = cash;
        this.shopper = shopper;
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

    public Product[] getShopper() {
        return shopper;
    }

    public void setShopper(Product[] shopper) {
        this.shopper = shopper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return getCash() == person.getCash() && Objects.equals(getName(), person.getName()) && Objects.deepEquals(getShopper(), person.getShopper());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCash(), Arrays.hashCode(getShopper()));
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cash=" + cash +
                ", shopper=" + Arrays.toString(shopper) +
                '}';
    }
}
