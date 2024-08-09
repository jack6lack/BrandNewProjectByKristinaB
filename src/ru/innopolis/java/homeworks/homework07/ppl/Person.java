package ru.innopolis.java.homeworks.homework07.ppl;

import ru.innopolis.java.homeworks.homework07.product.DiscountProduct;
import ru.innopolis.java.homeworks.homework07.product.Product;
import ru.innopolis.java.homeworks.homework07.support.DiscountAmountHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private Double cash;
    private Character gender;
    private Integer age;
    private List<Product> shopper = new ArrayList<>();
    private DiscountAmountHandler discountAmountHandler;

    public Person(String name, Double cash, Character gender, Integer age) {
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

    public Double getCash() {
        return this.cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Character getGender() {
        return gender;
    }

    public List<Product> getShopper() {
        return shopper;
    }

    public void addToShopper(Product product, List<Product> shopper) {
        this.shopper = shopper;
        shopper.add(product);
    }

    public DiscountAmountHandler getDiscountAmountHandler() {
        return this.discountAmountHandler;
    }

    public void setDiscountAmountHandler(DiscountAmountHandler discountAmountHandler) {
        this.discountAmountHandler = discountAmountHandler;
    }

    private String fDiscounts(List<Product> products) {
        StringBuilder sb = new StringBuilder();
        for (Product product : products) {
            if (product instanceof DiscountProduct) {
                sb.append(((DiscountProduct) product).toString(((DiscountProduct) product).getDiscountAmountHandler())).append("; ");
            } else {
                sb.append(product.toString()).append("; ");
            }
        }
        return sb.toString();
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
        return (!this.shopper.isEmpty() ? this.name + " - " + fDiscounts(this.shopper) : this.name + " - Ничего не куплено");
    }
}
