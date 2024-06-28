package ru.innopolis.java.homeworks.homework06.ppl;

import ru.innopolis.java.homeworks.homework06.DiscountProduct;
import ru.innopolis.java.homeworks.homework06.Product;

public class Pensioners extends Person {
    private boolean isOnSale;

    public Pensioners(String name, double cash, char gender, int age) {
        super(name, cash, gender, age);
    }

    @Override
    public double getCash() {
        if (super.getCash() < 0) {
            System.out.println("недостаточно монет");
        }
        return super.getCash();
    }

    private void checkAge(int age) {
        if (age < 65) {
            throw new IllegalArgumentException("указан некорректный возраст");
        }
    }

    public void checkIfProductIsOnSale(Product product) {
        isOnSale = product instanceof DiscountProduct;
    }

    public boolean isOnSale() {
        return isOnSale;
    }
}
