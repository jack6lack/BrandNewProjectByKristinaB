package ru.innopolis.java.homeworks.homework06.product;

import ru.innopolis.java.homeworks.homework06.support.DiscountAmountHandler;

import java.util.Objects;

public class DiscountProduct extends RegularProduct {

    private DiscountAmountHandler discountAmountHandler;

    public DiscountProduct(String nameOfProduct, int price, DiscountAmountHandler discountAmountHandler) {
        super(nameOfProduct, price);
        this.discountAmountHandler = discountAmountHandler;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountProduct that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(discountAmountHandler, that.discountAmountHandler);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountAmountHandler);
    }

    @Override
    public String toString() {
        return super.toString() + ", " + discountAmountHandler.getDiscountAmount() + "%";
    }
}
