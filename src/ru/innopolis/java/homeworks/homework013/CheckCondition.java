package ru.innopolis.java.homeworks.homework013;

import ru.innopolis.java.homeworks.homework08.Person;
import ru.innopolis.java.homeworks.homework08.Product;

public class CheckCondition {
    public static Integer calculateThinnerIntegerCash(Person person, Product product, ByCondition<Integer> condition) {
        int thinnerCash = person.getCash() - product.getPrice();
        if (checkIntegerCashAndPrice(person, product, condition) && condition.isOk(thinnerCash)) {
            return thinnerCash;
        } else {
            throw new IllegalArgumentException(person.getName() + " нет возможности купить товар " + product.getNameOfProduct());
        }
    }

    public static Double calculateThinnerDoubleCash(Person person, Product product, ByCondition<Double> condition) {
        double thinnerCash = person.getCash() - product.getPrice();
        if (checkDoubleCashAndPrice(person, product, condition) && condition.isOk(thinnerCash)) {
            return thinnerCash;
        } else {
            throw new IllegalArgumentException(person.getName() + " нет возможности купить товар " + product.getNameOfProduct());
        }
    }

    private static boolean checkIntegerCashAndPrice(Person person, Product product, ByCondition<Integer> condition) {
        if (condition.isOk(person.getCash()) && condition.isOk(product.getPrice())) {
            return true;
        } else {
            throw new IllegalArgumentException("деньги не могут быть отрицательными");
        }
    }

    private static boolean checkDoubleCashAndPrice(Person person, Product product, ByCondition<Double> condition) {
        if (condition.isOk((double) person.getCash()) && condition.isOk((double) product.getPrice())) {
            return true;
        } else {
            throw new IllegalArgumentException("деньги не могут быть отрицательными");
        }
    }
}
