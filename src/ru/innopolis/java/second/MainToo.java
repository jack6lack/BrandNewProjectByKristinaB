package ru.innopolis.java.second;


import java.util.Scanner;

public class MainToo {
    public static void main(String[] args) {
        //1
        Scanner scanner = new Scanner(System.in);
        System.out.println("������� ������� � �������� ����������: ");
        float f = scanner.nextFloat();
        makeCelsius(f);

        //2
        System.out.println("����� ���: ");
        int a = scanner.nextInt();
        System.out.println("����� ���: ");
        int b = scanner.nextInt();
        makeLotsMaths(a, b);

        //3
        System.out.println("������ ��� ����� � ���������, ��� �����: ");
        String word = scanner.next();
        System.out.println("� ��� �����: ");
        int count = scanner.nextInt();
        StringBuilder stringBuilder = new StringBuilder(word);
        for (int i = 0; i < count; i++) {
            System.out.println(stringBuilder);
            stringBuilder.append(word);
        }

        //4
        System.out.println("������� ������, ����� �����, ��������� �����");
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
        System.out.println("t " + f + " �������� �� ���������� ����� " + c + " �� �������");
        if (c < -50) {
            System.out.println("�������������");
        } else if (-50 <= c && c < 20) {
            System.out.println("����");
        } else {
            System.out.println("���������");
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

        System.out.println("����� ���� ����� �����: " + abAdd);
        System.out.println("������� ���� ����� �����: " + abSub);
        System.out.println("������������ �� ���� ����� �����: " + abMult);
        System.out.println("������� �� ���� ����� �����: " + abAver);
        System.out.println("���������� ���� ����� �����: " + abDist);
        System.out.println("������������ ����� �����: " + abMax);
        System.out.println("����������� ����� �����: " + abMin);
    }
}
