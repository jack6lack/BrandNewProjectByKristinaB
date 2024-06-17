package ru.innopolis.java.homeworks.homework03;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Television tv1 = new Television();
        tv1.setModel("sumsuk");
        tv1.setSmart(true);
        tv1.setDiscount(15);
        tv1.setPriceWithoutDiscount(100_000);
        System.out.println(tv1);

        Television tv2 = new Television("lgeee", 30_000, 0, false);
        System.out.println(tv2);

        Television tv3 = new Television("xixiomi", 49_999, 4.99, true);
        System.out.println(tv3);

        Television tv4 = new Television();
        System.out.println("введите желаемую модель: ");
        tv4.setModel(scanner.next());
        System.out.println("введите желаемую цену: ");
        tv4.setPriceWithoutDiscount(scanner.nextDouble());
        System.out.println("введите желаемую скидку: ");
        tv4.setDiscount(scanner.nextDouble());
        System.out.println("вам необходимы смарт функции? ( y / n )");
        if (scanner.next().equals("y")) {
            tv4.setSmart(true);
        } else {
            tv4.setSmart(false);
        }
        System.out.println("ваша итоговая цена: " + tv4.getFinalPrice());
        System.out.println(tv4);

        Television tv5 = new Television();
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("хотите случайный телевизор? (y / n)");
        String st = scanner.next();
        if (st.equals("y")) {
            for (int i = 0; i < random.nextInt(5, 26); i++) {
                stringBuilder.append(tv5.getAlphabet()[random.nextInt(26)]);
            }
            tv5.setModel(stringBuilder.toString());
            System.out.println("полученная модель: " + tv5.getModel());
            tv5.setPriceWithoutDiscount(Math.abs(random.nextInt(1_000, 1_000_000)));
            System.out.println("полученная цена: " + tv5.getPriceWithoutDiscount());
            tv5.setDiscount(Math.abs(random.nextInt(100)));
            System.out.println("полученная скидка: " + tv5.getDiscount());
            System.out.println("ваша итоговая цена: " + tv5.getFinalPrice());
            tv5.setSmart(random.nextBoolean());
        } else if (st.equals("n")) {
            System.out.println("ну и ладно, нет так нет");
        } else {
            System.out.println("в следующий раз читайте внимательнее, магии не будет");
        }
        if (tv5.getModel() == null) {
            System.out.println("пака");
        } else {
            System.out.println(tv5);
        }
    }
}
