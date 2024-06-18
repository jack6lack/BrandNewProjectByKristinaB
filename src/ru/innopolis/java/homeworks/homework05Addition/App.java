package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //1 - создается массив телевизоров, добавление новых элементов происходит в цикле
        Television[] televisions = new Television[10];

        for (int i = 0; i < televisions.length; i++) {
            televisions[i] = new Television("theCoolestTV" + i, random.nextInt(100_000),
                    random.nextInt(100), random.nextBoolean(), random.nextInt(100), random.nextInt(0, 101), random.nextBoolean());
        }
        //2 - в цикле выводятся только те тв, чей уровень громкости меньше или равен допустимому (что вводится с клавиатуры) И те, что включены
        System.out.println("введите уровень допустимого значения громкости звука (рекомендованный уровень: от 50 до 70)");
        int maxVolume = scanner.nextInt();
        for (Television television : televisions) {
            if (television.getVolumeLevel() <= maxVolume && television.isTurnedOn()) {
                System.out.println(television);
            }
        }
        //3 - сортировка по выбранному каналу (с выводом, чтобы было наглядно)
        System.out.println("приготовьтесь к сортировке по номеру канала: ");
        Arrays.sort(televisions, new Comparator<Television>() { //я уже посмотрела, шо тут надо делать через лямбды, но мне пока так понятнее
            @Override
            public int compare(Television o1, Television o2) {
                return Integer.compare(o1.getChannelNumber(), o2.getChannelNumber());
            }
        });
        System.out.println(Arrays.toString(televisions));
    }
}
