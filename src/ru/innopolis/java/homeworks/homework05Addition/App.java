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
                    random.nextInt(100), random.nextBoolean(), random.nextInt(0, 101), random.nextBoolean(), random.nextInt(0, 100));
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
                return Integer.compare(o1.getCurrentChannel(), o2.getCurrentChannel());
            }
        });
        System.out.println(Arrays.toString(televisions));
        //6.add
        Program[] programs = new Program[10];
        programs[0] = new Program("чип и дейл", 10, 100);
        programs[1] = new Program("слово пастыря", 4, 34);
        programs[2] = new Program("топ-10 музыкальных исполнителей по версии юлия цезаря", 8, 76);
        programs[3] = new Program("новости дудинки", 2, 2);
        programs[4] = new Program("в мире ракообразных", 7, 1);
        programs[5] = new Program("процессоры нового поколения", 10, 20);
        programs[6] = new Program("презентация 112 айфона", 8, 1_000);
        programs[7] = new Program("смешные котики", 10, 10_000);
        programs[8] = new Program("смешные собачки", 10, 10_000);
        programs[9] = new Program("диалоги о рыбалке", 7, 25);
        Channel[] channels = new Channel[10];
        for (int i = 0; i < channels.length; i++) {
            channels[i] = new Channel("канал", i + 1, programs[i]);
            System.out.println(channels[i]);
        }
        Television t = new Television("m", 14, true, channels);
        System.out.println("выберите номер канала: ");
        int channelNumber = scanner.nextInt();
        t.goToSelectedChannel(channelNumber);
    }
}
