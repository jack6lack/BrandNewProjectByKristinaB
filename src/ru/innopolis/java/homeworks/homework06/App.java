package ru.innopolis.java.homeworks.homework06;

import ru.innopolis.java.homeworks.homework08.ppl.Children;
import ru.innopolis.java.homeworks.homework08.ppl.Pensioners;
import ru.innopolis.java.homeworks.homework08.ppl.Person;
import ru.innopolis.java.homeworks.homework08.product.DiscountProduct;
import ru.innopolis.java.homeworks.homework08.product.Product;
import ru.innopolis.java.homeworks.homework08.product.RegularProduct;
import ru.innopolis.java.homeworks.homework08.support.DiscountAmountHandler;
import ru.innopolis.java.homeworks.homework08.support.ShoppingHandler;
import ru.innopolis.java.homeworks.homework08.support.TxtDataHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class App {

    private static DiscountAmountHandler dah;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalTime lt = LocalTime.now();
        dah = new DiscountAmountHandler(lt, null);
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
                    }
                    counter++;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
