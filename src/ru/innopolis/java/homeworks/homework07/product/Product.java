package ru.innopolis.java.homeworks.homework07.product;

public interface Product {
    String getNameOfProduct();
    Double getPrice();
    boolean isMatchesNamingRule(String nameForCheck);
    boolean isMatchesPricingRule(Double priceForCheck);
}
