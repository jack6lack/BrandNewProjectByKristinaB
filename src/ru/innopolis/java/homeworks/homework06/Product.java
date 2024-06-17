package ru.innopolis.java.homeworks.homework06;

import java.util.Objects;

public class Product {
    private String nameOfProduct;
    private int price;

    public Product(String nameOfProduct, int price) {
        this.nameOfProduct = nameOfProduct;
        this.price = price;
    }

    public String getNameOfProduct() {
        if (this.nameOfProduct == null) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public int getPrice() {
        if (this.price < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        return "Product{" +
                "nameOfProduct='" + nameOfProduct + '\'' +
                ", price=" + price +
                '}';
    }
}
