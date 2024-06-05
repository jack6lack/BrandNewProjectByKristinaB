package ru.innopolis.java.third;

import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        TV tv1 = new TV();
        tv1.setModel("sumsuk");
        tv1.setSmart(true);
        tv1.setDiscount(15);
        tv1.setPrice(100_000);
        System.out.println(tv1);

        TV tv2 = new TV("lgeee", 30_000, 0, false);
        System.out.println(tv2);

        TV tv3 = new TV("xixiomi", 49_999, 4.99, true);
        System.out.println(tv3);

        TV tv4 = new TV(scanner);
        System.out.println(tv4);

        TV tv5 = new TV(random, scanner);
        if(tv5.getModel()==null){
            System.out.println("пака");
        } else {
            System.out.println(tv5);
        }

    }
}
