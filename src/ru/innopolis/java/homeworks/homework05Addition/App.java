package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //1 - ��������� ������ �����������, ���������� ����� ��������� ���������� � �����
        Television[] televisions = new Television[10];

        for (int i = 0; i < televisions.length; i++) {
            televisions[i] = new Television("theCoolestTV" + i, random.nextInt(100_000),
                    random.nextInt(100), random.nextBoolean(), random.nextInt(100), random.nextInt(0, 101), random.nextBoolean());
        }
        //2 - � ����� ��������� ������ �� ��, ��� ������� ��������� ������ ��� ����� ����������� (��� �������� � ����������) � ��, ��� ��������
        System.out.println("������� ������� ����������� �������� ��������� ����� (��������������� �������: �� 50 �� 70)");
        int maxVolume = scanner.nextInt();
        for (Television television : televisions) {
            if (television.getVolumeLevel() <= maxVolume && television.isTurnedOn()) {
                System.out.println(television);
            }
        }
        //3 - ���������� �� ���������� ������ (� �������, ����� ���� ��������)
        System.out.println("������������� � ���������� �� ������ ������: ");
        Arrays.sort(televisions, new Comparator<Television>() { //� ��� ����������, �� ��� ���� ������ ����� ������, �� ��� ���� ��� ��������
            @Override
            public int compare(Television o1, Television o2) {
                return Integer.compare(o1.getChannelNumber(), o2.getChannelNumber());
            }
        });
        System.out.println(Arrays.toString(televisions));
    }
}
