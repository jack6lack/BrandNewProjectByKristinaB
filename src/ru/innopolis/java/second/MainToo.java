package ru.innopolis.java.second;


import java.util.Scanner;

public class MainToo {
    public static void main(String[] args) {
        //1
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите степень в градусах фаренгейта: ");
        float f = scanner.nextFloat();
        makeCelsius(f);

        //2
        System.out.println("число рас: ");
        int a = scanner.nextInt();
        System.out.println("число два: ");
        int b = scanner.nextInt();
        makeLotsMaths(a, b);

        //3
        System.out.println("введем тут слово и посмотрим, что будет: ");
        String word = scanner.next();
        System.out.println("а тут цифру: ");
        int count = scanner.nextInt();
        StringBuilder stringBuilder = new StringBuilder(word);
        for (int i = 0; i < count; i++) {
            System.out.println(stringBuilder);
            stringBuilder.append(word);
        }

        //4
        System.out.println("сначала символ, потом цифру, получится сетка");
        String someElement = scanner.next();
        int columnsAndLines = scanner.nextInt();
        for (int i = 0; i < columnsAndLines; i++) {
            for (int j = 0; j < columnsAndLines; j++) {
                System.out.print(someElement);
            }
            System.out.println();
        }
    }

    private static void makeCelsius(float f) {

        float c = (f - 32) * 5 / 9;
        System.out.println("t " + f + " градусов по фаренгейту равна " + c + " по цельсию");
        if (c < -50) {
            System.out.println("прохладненько");
        } else if (-50 <= c && c < 20) {
            System.out.println("норм");
        } else {
            System.out.println("жарковато");
        }
    }

    private static void makeLotsMaths(int a, int b) {

        int abAdd = Math.addExact(a, b);
        int abSub = Math.subtractExact(a, b);
        int abMult = Math.multiplyExact(a, b);
        int abAver = Math.addExact(a, b) / 2;
        int abDist = Math.abs(Math.subtractExact(a, b));
        int abMax = Math.max(a, b);
        int abMin = Math.min(a, b);

        System.out.println("сумма двух целых чисел: " + abAdd);
        System.out.println("разница двух целых чисел: " + abSub);
        System.out.println("произведение из двух целых чисел: " + abMult);
        System.out.println("среднее из двух целых чисел: " + abAver);
        System.out.println("расстояние двух целых чисел: " + abDist);
        System.out.println("максимальное целое число: " + abMax);
        System.out.println("минимальное целое число: " + abMin);
    }
}
