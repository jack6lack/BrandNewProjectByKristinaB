package ru.innopolis.java.homeworks.homework06;

import ru.innopolis.java.homeworks.homework06.support.DiscountAmountHandler;

public class DiscountProduct extends Product {

    DiscountAmountHandler discountAmountHandler;

    public DiscountProduct(String nameOfProduct, int price, DiscountAmountHandler discountAmountHandler) {
        super(nameOfProduct, price);
        this.discountAmountHandler = discountAmountHandler;
    }
}
