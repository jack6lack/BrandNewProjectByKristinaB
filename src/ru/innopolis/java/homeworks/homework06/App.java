package ru.innopolis.java.homeworks.homework06;

import ru.innopolis.java.homeworks.homework06.ppl.Adults;
import ru.innopolis.java.homeworks.homework06.ppl.Children;
import ru.innopolis.java.homeworks.homework06.ppl.Pensioners;
import ru.innopolis.java.homeworks.homework06.ppl.Person;
import ru.innopolis.java.homeworks.homework06.product.DiscountProduct;
import ru.innopolis.java.homeworks.homework06.product.Product;
import ru.innopolis.java.homeworks.homework06.product.RegularProduct;
import ru.innopolis.java.homeworks.homework06.support.DiscountAmountHandler;
import ru.innopolis.java.homeworks.homework06.support.ShoppingHandler;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class App {

    private static DiscountAmountHandler dah;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalTime lt = LocalTime.now();
        dah = new DiscountAmountHandler(lt, null);

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(new Pensioners("Павел Андреевич", 10_000, 'm', 66));
        personList.add(new Pensioners("Анна Петровна", 2_000, 'f', 65));
        personList.add(new Adults("Борис", 10, 'm', 44));
        personList.add(new Children("Женя", 0, 'f', 16));
        personList.add(new Children("Света", -3, 'f', 16));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(new DiscountProduct("Хлеб", 40, dah));
        productList.add(new DiscountProduct("Молоко", 60, dah));
        productList.add(new DiscountProduct("Торт", 1000, dah));
        productList.add(new RegularProduct("Кофе растворимый", 879));
        productList.add(new RegularProduct("Масло", 150));
        productList.add(new RegularProduct("Мороженое", 200));
        productList.add(new RegularProduct("Макароны", 800));
        productList.add(new RegularProduct("888", 100));
        productList.add(new RegularProduct("ен", 100));
        productList.add(new RegularProduct("Шоколадка", 0));

        String checkNameOrEnd;
        String checkProduct = "";

        System.out.println("начинаем покупки: ");
        do {
//            checkNameOrEnd = scanner.nextLine().replaceAll("\\s", "").toLowerCase();
            checkNameOrEnd = scanner.nextLine();
//            checkProduct = scanner.nextLine().replaceAll("\\s", "").toLowerCase();
            if (!checkNameOrEnd.equals("END")) {
                checkProduct = scanner.nextLine();
            }
            for (Person person : personList) {
                if (person.getName().equals(checkNameOrEnd)) {
                    for (Product product : productList) {
                        if (product.getNameOfProduct().equals(checkProduct)) {
                            if (product instanceof DiscountProduct) {
                                dah = new DiscountAmountHandler(lt, person);
                                superAddinToShopper(person, (DiscountProduct) product, dah);
                            } else {
                                superAddinToShopper(person, (RegularProduct) product);
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        } while (!Objects.equals(checkNameOrEnd, "END"));

        //люди и покупки
        for (Person person : personList) {
            System.out.println(person);
        }
        //акционные, неакционные товары и недопустимые товары
        ArrayList<Product> regularProducts = new ArrayList<>();
        ArrayList<Product> discountProducts = new ArrayList<>();
        ArrayList<Product> deletedProducts = new ArrayList<>();
        for (Product product : productList) {
            if (!product.isMatchesNamingRule(product.getNameOfProduct()) || !product.isMatchesPricingRule(product.getPrice())) {
                deletedProducts.add(product);
            } else if (product instanceof DiscountProduct) {
                discountProducts.add(product);
            } else {
                regularProducts.add(product);
            }
        }
        System.out.println("обычные продукты: " + regularProducts + "\nакционные продукты: " + discountProducts + "\nудаленные продукты: " + deletedProducts);
    }

    static boolean isProductCompliesWithTheRules(Product product) {
        return product.getNameOfProduct() != null && product.getPrice() != 0;
    }

    static boolean superBuyinForMoney(Person person, RegularProduct regularProduct) {
        if (isProductCompliesWithTheRules(regularProduct)) {
            if (person instanceof Children) {
                if (((Children) person).isAbleToBuy()) {
                    return ShoppingHandler.buyinForMoney(person, regularProduct);
                } else {
                    return false;
                }
            } else if (person instanceof Pensioners) {
                if (regularProduct instanceof DiscountProduct) {
                    return ShoppingHandler.buyinForMoney(person, regularProduct);
                } else {
                    return false;
                }
            } else {
                ShoppingHandler.buyinForMoney(person, regularProduct);
                return true;
            }
        } else {
            return false;
        }
    }

    static boolean superBuyinForMoney(Person person, DiscountProduct product, DiscountAmountHandler dah) {
        if (isProductCompliesWithTheRules(product)) {
            if (person instanceof Children) {
                if (((Children) person).isAbleToBuy()) {
                    return ShoppingHandler.buyinForMoney(person, product, dah);
                } else {
                    return false;
                }
            } else if (person instanceof Pensioners) {
                return ShoppingHandler.buyinForMoney(person, product, dah);
            } else {
                ShoppingHandler.buyinForMoney(person, product, dah);
                return true;
            }
        } else {
            return false;
        }
    }

    public static void superAddinToShopper(Person person, RegularProduct regularProduct) {
        if (superBuyinForMoney(person, regularProduct)) {
            ShoppingHandler.addinToShopper(person, regularProduct);
        } else {
            System.out.println(person.getName() + " не может позволить себе " + regularProduct.getNameOfProduct());
        }
    }

    public static void superAddinToShopper(Person person, DiscountProduct product, DiscountAmountHandler dah) {
        if (superBuyinForMoney(person, product, dah)) {
            ShoppingHandler.addinToShopper(person, product, dah);
        } else {
            System.out.println(person.getName() + " не может позволить себе " + product.getNameOfProduct());
        }
    }
}
