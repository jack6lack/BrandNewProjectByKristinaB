package ru.innopolis.java.homeworks.homework06.support;

import ru.innopolis.java.homeworks.homework06.DiscountProduct;
import ru.innopolis.java.homeworks.homework06.Product;
import ru.innopolis.java.homeworks.homework06.ppl.Adults;
import ru.innopolis.java.homeworks.homework06.ppl.Person;

public class ShoppingHandler {

    private static boolean isSuccessfulPurchase;

    public static boolean isSuccessfulPurchase() {
        return isSuccessfulPurchase;
    }


    public static void buyinForMoney(Person person, Product product) {
        double thinnerCash = person.getCash() - product.getPrice();
        if (thinnerCash < 0) {
            if (person instanceof Adults) {
                person.setCash(thinnerCash);
                isSuccessfulPurchase = true;
            } else {
                isSuccessfulPurchase = false;
            }
        } else {
            person.setCash(thinnerCash);
            isSuccessfulPurchase = true;
        }
    }

    public static boolean buyinForMoney(Person person, DiscountProduct discountProduct, DiscountAmountHandler dah) {
        double thinnerCash = person.getCash() - (discountProduct.getPrice() - (dah.getDiscountAmount() != 0 ?
                discountProduct.getPrice() / 100 * dah.getDiscountAmount() : 0));
        if (thinnerCash < 0) {
            if (person instanceof Adults) {
                person.setCash(thinnerCash);
                return true;
            } else {
                return false;
            }
        } else {
            person.setCash(thinnerCash);
            return true;
        }
    }

    public static void addinToShopper(Person person, Product product) {
        person.addToShopper(product, person.getShopper());
        System.out.println(person.getName() + (person.getGender() == 'm' ? " купил " : " купила ") + product.getNameOfProduct());
    }

    public static void addinToShopper(Person person, DiscountProduct discountProduct, DiscountAmountHandler dah) {
        if (buyinForMoney(person, discountProduct, dah)) {
            person.addToShopper(discountProduct, person.getShopper());
            System.out.println(person.getName() + (person.getGender() == 'm' ? " купил " : " купила ") + discountProduct.getNameOfProduct());
        } else {
            System.out.println(person.getName() + " не может позволить себе " + discountProduct.getNameOfProduct());
        }
    }
}
