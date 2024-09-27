package ru.innopolis.java.homeworks.homework07.support;

import ru.innopolis.java.homeworks.homework07.product.DiscountProduct;
import ru.innopolis.java.homeworks.homework07.product.RegularProduct;
import ru.innopolis.java.homeworks.homework07.ppl.Adults;
import ru.innopolis.java.homeworks.homework07.ppl.Person;

public class ShoppingHandler {

    private ShoppingHandler() {
    }

    public static boolean buyinForMoney(Person person, RegularProduct regularProduct) {
        Double thinnerCash = person.getCash() - regularProduct.getPrice();
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
//        person.setDiscountAmountHandler(dah);
        Double thinnerCash = person.getCash() - (discountProduct.getPrice() - (discountProduct.getPrice() / 100 * dah.getDiscountAmount()));
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
