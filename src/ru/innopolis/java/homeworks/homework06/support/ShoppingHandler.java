package ru.innopolis.java.homeworks.homework06.support;

import ru.innopolis.java.homeworks.homework06.product.DiscountProduct;
import ru.innopolis.java.homeworks.homework06.product.RegularProduct;
import ru.innopolis.java.homeworks.homework06.ppl.Adults;
import ru.innopolis.java.homeworks.homework06.ppl.Person;

public class ShoppingHandler {

    public static boolean buyinForMoney(Person person, RegularProduct regularProduct) {
        double thinnerCash = person.getCash() - regularProduct.getPrice();
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

    public static void addinToShopper(Person person, RegularProduct regularProduct) {
        person.addToShopper(regularProduct, person.getShopper());
        System.out.println(person.getName() + (person.getGender() == 'm' ? " купил " : " купила ") + regularProduct.getNameOfProduct());
    }

    public static void addinToShopper(Person person, DiscountProduct discountProduct, DiscountAmountHandler dah) {
        person.addToShopper(discountProduct, person.getShopper());
        System.out.println(person.getName() + (person.getGender() == 'm' ? " купил " : " купила ") + discountProduct.getNameOfProduct());

    }
}
