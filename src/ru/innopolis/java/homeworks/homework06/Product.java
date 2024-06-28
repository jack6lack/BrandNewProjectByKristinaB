package ru.innopolis.java.homeworks.homework06;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Objects;

public class Product {
    private String nameOfProduct;
    private double price;

    public Product(String nameOfProduct, int price) {
//        this.nameOfProduct = nameOfProduct.replaceAll("\\s", "").toLowerCase();
        this.nameOfProduct = nameOfProduct;
        getNameOfProduct();
        this.price = price;
    }

    public String getNameOfProduct() {
        if (!isMatchesNamingRule(this.nameOfProduct)) {
            throw new IllegalArgumentException("Название продукта не может быть пустым, состоящим только из цифр или короче 3 символов");
        }
        return this.nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
        getNameOfProduct();
    }

    public double getPrice() {
        if (!isMatchesPricingRule(this.price)) {
            throw new IllegalArgumentException("Цена не может быть отрицательной или равной нулю");
        }
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
        getPrice();
    }

    private boolean isMatchesNamingRule(String nameForCheck) {
        if (nameForCheck.isBlank()
                || nameForCheck.length() < 3
                || nameForCheck.matches("[0-9]+")) {
            return false;
        }
        return true;
    }

    private boolean isMatchesPricingRule(double priceForCheck) {
        if (priceForCheck <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return getPrice() == product.getPrice() && Objects.equals(getNameOfProduct(), product.getNameOfProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameOfProduct(), getPrice());
    }

    @Override
    public String toString() {
        return nameOfProduct;
    }
}
