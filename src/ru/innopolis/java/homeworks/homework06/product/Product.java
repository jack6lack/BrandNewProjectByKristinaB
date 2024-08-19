package ru.innopolis.java.homeworks.homework06.product;

public interface Product {
    String getNameOfProduct();
    double getPrice();
    boolean isMatchesNamingRule(String nameForCheck);
    boolean isMatchesPricingRule(double priceForCheck);
}
