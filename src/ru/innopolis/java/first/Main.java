package ru.innopolis.java.first;

import java.util.Random;

/**
 * вау!
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("имя");
//        String name = scanner.nextLine();
//
//        int start = 1;
//        int end = 5;
//
//        for (int i = start; i < end; i++) {
//            System.out.println("hey, " + name);
//        }

        //1
        System.out.println(4 + " " + 6 + " " + 2 + " " + 99);
        //2
        Random random1 = new Random();
        Random random2 = new Random();

        String[] variants = {"камень", "ножницы", "бумага"};
        String player1 = "вася";
        String player2 = "петя";

        int i = random1.nextInt(2);
        int o = random2.nextInt(2);

        String iS = String.valueOf(i);
        String oS = String.valueOf(o);
        String res = iS + oS;

        rollFigure(player1, variants[i]);
        rollFigure(player2, variants[o]);

        String winner = null;

        switch (res) {
            case "01", "12", "20":
                winner = player1;
                break;
            case "02", "10", "21":
                winner = player2;
                break;
            default:
                System.out.println("ничья!");
                break;
        }

        if (winner != null) {
            System.out.println(winner + " побеждает!");
        }
    }

    private static void rollFigure(String name, String variant) throws InterruptedException {
        System.out.print(name + " бросает...");
        Thread.sleep(2000);
        System.out.println(variant + "!");
    }
}