package ru.innopolis.java.homeworks.homework06;

import ru.innopolis.java.homeworks.homework06.ppl.Adults;
import ru.innopolis.java.homeworks.homework06.ppl.Children;
import ru.innopolis.java.homeworks.homework06.ppl.Pensioners;
import ru.innopolis.java.homeworks.homework06.ppl.Person;
import ru.innopolis.java.homeworks.homework06.support.DiscountAmountHandler;
import ru.innopolis.java.homeworks.homework06.support.ShoppingHandler;

import java.time.LocalTime;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LocalTime lt = LocalTime.now();

//        ArrayList<Person> personList = new ArrayList<>();
//        personList.add(new Person("Павел Андреевич", 10_000, 'm', 66));
//        personList.add(new Person("Анна Петровна", 2_000, 'f', 65));
//        personList.add(new Person("Борис", 10, 'm', 44));
//        personList.add(new Person("Женя", 0, 'f', 16));
//        personList.add(new Person("Света", -3, 'f', 18));
//
//        ArrayList<Product> productList = new ArrayList<>();
//        productList.add(new Product("Хлеб", 40));
//        productList.add(new Product("Молоко", 60));
//        productList.add(new Product("Торт", 1000));
//        productList.add(new Product("Кофе растворимый", 879));
//        productList.add(new Product("Масло", 150));
//        productList.add(new Product("Мороженое", 200));
//        productList.add(new Product("Макароны", 800));

        String checkNameOrEnd;
        String checkProduct = "";

        System.out.println("начинаем покупки: ");
//        do {
////            checkNameOrEnd = scanner.nextLine().replaceAll("\\s", "").toLowerCase();
//            checkNameOrEnd = scanner.nextLine();
////            checkProduct = scanner.nextLine().replaceAll("\\s", "").toLowerCase();
//            if (!checkNameOrEnd.equals("END")) {
//                checkProduct = scanner.nextLine();
//            }
//            for (Person person : personList) {
//                if (person.getName().equals(checkNameOrEnd)) {
//                    for (Product product : productList) {
//                        if (product.getNameOfProduct().equals(checkProduct)) {
//                            addinToShopper(person, product);
//                            break;
//                        }
//                    }
//                    break;
//                }
//            }
//        } while (!Objects.equals(checkNameOrEnd, "END"));
//        for (Person person : personList) {
//            System.out.println(person);
//        }

        Person sanya = new Adults("саня", 10, 'm', 19);
        DiscountAmountHandler dah = new DiscountAmountHandler(lt, sanya);
        DiscountProduct dp = new DiscountProduct("хлеп", 10, dah);
        Product p = new Product("нут", 400);
        superAddinToShopper(sanya, p);
        System.out.println(sanya);
        System.out.println(dp.getPrice());
        System.out.println(dah.getDiscountAmount());
        System.out.println(sanya.getCash());
    }

    static boolean superBuyinForMoney(Person person, Product product) {
        if (person instanceof Children) {
            if (((Children) person).isAbleToBuy() == true) {
                ShoppingHandler.buyinForMoney(person, product);
                return true;
            } else {
                System.out.println("проходи, не задерживайся");
                return false;
            }
        } else if (person instanceof Pensioners) {
            ((Pensioners) person).checkIfProductIsOnSale(product);
            if (((Pensioners) person).isOnSale() == true) {
                ShoppingHandler.buyinForMoney(person, product);
                return true;
            } else {
                System.out.println("а при советском союзе все бесплатное было");
                return false;
            }
        } else {
            ShoppingHandler.buyinForMoney(person, product);
            return true;
        }
    }

    public static void superAddinToShopper(Person person, Product product) {
        superBuyinForMoney(person, product);
        if (ShoppingHandler.isSuccessfulPurchase()) {
            ShoppingHandler.addinToShopper(person, product);
        } else {
            System.out.println(person.getName() + " не может позволить себе " + product.getNameOfProduct());
        }
    }
}
