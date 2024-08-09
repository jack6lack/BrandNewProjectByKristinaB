package ru.innopolis.java.homeworks.homework07;

import ru.innopolis.java.homeworks.homework07.ppl.Adults;
import ru.innopolis.java.homeworks.homework07.ppl.Children;
import ru.innopolis.java.homeworks.homework07.ppl.Pensioners;
import ru.innopolis.java.homeworks.homework07.ppl.Person;
import ru.innopolis.java.homeworks.homework07.product.DiscountProduct;
import ru.innopolis.java.homeworks.homework07.product.Product;
import ru.innopolis.java.homeworks.homework07.product.RegularProduct;
import ru.innopolis.java.homeworks.homework07.support.DiscountAmountHandler;
import ru.innopolis.java.homeworks.homework07.support.ShoppingHandler;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalTime lt = LocalTime.now();

        String persons = scanner.nextLine();
        List<Person> personList = readPersonsMakeList(persons);

        List<Product> productList = new ArrayList<>();
        String checkProductOrEnd;
        do {
            checkProductOrEnd = scanner.nextLine();
            if (!checkProductOrEnd.equals("END")) {
                Product product = readLineMakeProduct(checkProductOrEnd);
                productList.add(product);
            }
        }
        while (!checkProductOrEnd.equals("END"));
        //акционные и неакционные товары
        List<Product> regularProducts = new ArrayList<>();
        List<Product> discountProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product instanceof DiscountProduct) {
                discountProducts.add(product);
            } else {
                regularProducts.add(product);
            }
        }
        System.out.println("обычные продукты: " + regularProducts + "\nакционные продукты: " + discountProducts);

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
                                DiscountAmountHandler dah;
                                if (!person.getShopper().contains(product)) {
                                    dah = new DiscountAmountHandler(lt, person, (DiscountProduct) product);
                                    DiscountProduct dp = new DiscountProduct(product.getNameOfProduct(), product.getPrice(), ((DiscountProduct) product).getDiscount());
                                    dp.setDiscountAmountHandler(dah);
                                    product = dp;
                                } else {
                                    dah = ((DiscountProduct) product).getDiscountAmountHandler();
                                }
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
    }

    private static List<Person> readPersonsMakeList(String line) {
        List<Person> personArrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder(line);
        Integer i, j, k, l;
        while (!sb.isEmpty()) {
            i = sb.indexOf(";");
            String personData = sb.substring(0, i).trim();
            while (!personData.isEmpty()) {
                j = personData.indexOf("=");
                k = personData.indexOf(",");
                l = personData.lastIndexOf(",");
                Character gender;
                String personName = personData.substring(0, j).trim();
                Double personCash = Double.parseDouble(personData.substring(j + 1, k).trim());
                Integer personAge = Integer.parseInt(personData.substring(l + 1).trim());
                if (personData.contains("m")) {
                    gender = 'm';
                } else {
                    gender = 'f';
                }
                if (personAge < 0) {
                    System.out.println("указан некорректный возраст");
                } else if (personAge < 18) {
                    personArrayList.add(new Children(personName, personCash, gender, personAge));
                } else if (personAge < 65) {
                    personArrayList.add(new Adults(personName, personCash, gender, personAge));
                } else {
                    personArrayList.add(new Pensioners(personName, personCash, gender, personAge));
                }
                personData = "";
            }
            sb.delete(0, i + 2);
        }
        return personArrayList;
    }

    private static Product readLineMakeProduct(String line) {

        Integer i = line.indexOf("=");
        Integer j = line.indexOf(",");
        String productName = line.substring(0, i).trim();
        Double productPrice;
        Integer productDiscount;
        if (line.contains("%")) {
            productPrice = Double.parseDouble(line.substring(i + 1, j).trim());
            productDiscount = Integer.parseInt(line.replace("%", "").substring(j + 1).trim());
            Product dp = new DiscountProduct(productName, productPrice, productDiscount);
            if (isProductCompliesWithTheRules(dp)) {
                return dp;
            }
        } else {
            productPrice = Double.parseDouble(line.substring(i + 1).trim());
            Product rp = new RegularProduct(productName, productPrice);
            if (isProductCompliesWithTheRules(rp)) {
                return rp;
            }
        }
        return null;
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
        if (person instanceof Pensioners) {
            System.out.println(person.getName() + " не желает покупать без скидки " + regularProduct.getNameOfProduct());
        } else if (superBuyinForMoney(person, regularProduct)) {
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
