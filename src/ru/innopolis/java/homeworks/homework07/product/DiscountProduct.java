package ru.innopolis.java.homeworks.homework07.product;

import ru.innopolis.java.homeworks.homework07.support.DiscountAmountHandler;

import java.util.Objects;

public class DiscountProduct extends RegularProduct {

    private DiscountAmountHandler discountAmountHandler;
    private Integer discount;

    public DiscountProduct(String nameOfProduct, Double price, Integer discount) {
        super(nameOfProduct, (double) Math.round(price / (100 - discount) * 100));
        this.discount = discount;
    }

    public void setDiscountAmountHandler(DiscountAmountHandler discountAmountHandler) {
        this.discountAmountHandler = discountAmountHandler;
    }

    public DiscountAmountHandler getDiscountAmountHandler() {
        return discountAmountHandler;
    }

    public Integer getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + discount + "%";
    }

    public String toString(DiscountAmountHandler discountAmountHandler) {
        return super.toString() + ", " + discountAmountHandler.getDiscountAmount() + "%";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountProduct that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(discountAmountHandler, that.discountAmountHandler) && Objects.equals(discount, that.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountAmountHandler, discount);
    }
}
