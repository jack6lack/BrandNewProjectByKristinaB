package ru.innopolis.java.homeworks.homework06.product;

import java.util.Objects;

public class RegularProduct implements Product {

    private String nameOfProduct;
    private double price;

    public RegularProduct(String nameOfProduct, int price) {
//        this.nameOfProduct = nameOfProduct.replaceAll("\\s", "").toLowerCase();
        this.nameOfProduct = nameOfProduct;
        getNameOfProduct();
        this.price = price;
    }

    @Override
    public String getNameOfProduct() {
        if (!isMatchesNamingRule(this.nameOfProduct)) {
            System.out.println("недопустимое имя продукта!");
            return null;
        } else {
            return this.nameOfProduct;
        }
    }

    @Override
    public double getPrice() {
        if (!isMatchesPricingRule(this.price)) {
            System.out.println("недопустимая стоимость продукта!");
            return 0;
        } else {
            return this.price;
        }
    }

    @Override
    public boolean isMatchesNamingRule(String nameForCheck) {
        if (nameForCheck.isBlank() || nameForCheck.length() < 3 || nameForCheck.matches("[0-9]+")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isMatchesPricingRule(double priceForCheck) {
        if (priceForCheck <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegularProduct regularProduct)) return false;
        return getPrice() == regularProduct.getPrice() && Objects.equals(getNameOfProduct(), regularProduct.getNameOfProduct());
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
