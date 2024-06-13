package ru.innopolis.java.homeworks.homework01;

import java.util.Random;

/**
 * ���!
 */

public class Main {

    public static void main(String[] args) throws InterruptedException {

        //1
        System.out.println(4 + " " + 6 + " " + 2 + " " + 99);
        //2
        Random random1 = new Random();
        Random random2 = new Random();

        String[] variants = {"������", "�������", "������"};
        String player1 = "����";
        String player2 = "����";

        int i = random1.nextInt(3);
        int o = random2.nextInt(3);

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
                System.out.println("�����!");
                break;
        }

        if (winner != null) {
            System.out.println(winner + " ���������!");
        }
    }

    private static void rollFigure(String name, String variant) throws InterruptedException {
        System.out.print(name + " �������...");
        Thread.sleep(2000);
        System.out.println(variant + "!");
    }
}