package ru.innopolis.java.homeworks.homework08;

import ru.innopolis.java.homeworks.homework08.ppl.Children;
import ru.innopolis.java.homeworks.homework08.ppl.Pensioners;
import ru.innopolis.java.homeworks.homework08.ppl.Person;
import ru.innopolis.java.homeworks.homework08.product.DiscountProduct;
import ru.innopolis.java.homeworks.homework08.product.Product;
import ru.innopolis.java.homeworks.homework08.product.RegularProduct;
import ru.innopolis.java.homeworks.homework08.support.ShoppingHandler;
import ru.innopolis.java.homeworks.homework08.support.TxtDataHandler;
import ru.innopolis.java.homeworks.homework08.support.TxtLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class App {

    static final TxtLogger txtLogger = new TxtLogger("report_on_task_8");

    public static void main(String[] args) {

        ArrayList<Person> personList = new ArrayList<>();
        ArrayList<Product> productList = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Path.of("resources/data.txt"))) {
            String condition = "";
            while (!condition.equals("END")) {
                int counter = 0;
                for (String line : br.lines().toList()) {
                    condition = line;
                    if (counter == 0) {
                        personList = TxtDataHandler.readPersonsMakeList(line);
                    }
                    if (counter == 1) {
                        productList = TxtDataHandler.readProductsMakeList(line);
                    }
                    if (counter > 1) {
                        if (line.equals("END")) {
                            break;
                        }
                        int i = line.indexOf("-");
                        String customerName = line.substring(0, i);
                        String productName = line.substring(i + 1);
                        for (Person person : personList) {
                            if (person.getName().equals(customerName)) {
                                for (Product product : productList) {
                                    if (product.getNameOfProduct().equals(productName)) {
                                        superAddinToShopper(person, (RegularProduct) product);
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //люди и покупки
        for (Person person : personList) {
            txtLogger.log(person.toString());
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
        txtLogger.log("обычные продукты: " + regularProducts + "\nакционные продукты: " + discountProducts + "\nудаленные продукты: " + deletedProducts);
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

    public static void superAddinToShopper(Person person, RegularProduct regularProduct) {
        if (superBuyinForMoney(person, regularProduct)) {
            ShoppingHandler.addinToShopper(person, regularProduct);
        } else {
            txtLogger.log(person.getName() + " не может позволить себе " + regularProduct.getNameOfProduct());
        }
    }
}
