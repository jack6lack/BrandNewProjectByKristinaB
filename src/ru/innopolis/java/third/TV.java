package ru.innopolis.java.third;

import java.util.Random;
import java.util.Scanner;

public class TV {
    private String model;
    private double price;
    private double discount;
    private boolean isSmart;
    private String isSmartButString;
    private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //���� ���� �� ����� ����� �����
    public TV() {
    }

    //���� ���� �����
    public TV(String model, double price, double discount, boolean isSmart) {
        this.model = model;
        this.discount = discount;
        this.price = recalculatePrice(price);
        this.isSmart = isSmart;
        smartToString(isSmart);
    }

    //���� ��������  ������� ��������� � ����� ����������� �����
    public TV(Scanner scanner) {
        System.out.println("������� �������� ������: ");
        this.model = scanner.next();
        System.out.println("������� �������� ����: ");
        this.price = recalculatePrice(scanner.nextDouble());
        System.out.println("������� �������� ������: ");
        this.discount = scanner.nextDouble();
        this.price = recalculatePrice(this.price);
        System.out.println("���� �������� ����: " + this.price);
        smartToString(this.isSmart);
    }

    //���� �������� ������ ��������� � ����� ����������� ����� ���������
    public TV(Random random, Scanner scanner) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("������ ��������� ���������? (y / n)");
        String st = scanner.next();
        if (st.equals("y")) {
            for (int i = 0; i < random.nextInt(5, 26); i++) {
                stringBuilder.append(alphabet[random.nextInt(26)]);
            }
            this.model = stringBuilder.toString();
            System.out.println("���������� ������: " + this.model);
            this.price = Math.abs(random.nextInt(1_000, 1_000_000));
            System.out.println("���������� ����: " + this.price);
            this.discount = Math.abs(random.nextInt(100));
            System.out.println("���������� ������: " + this.discount);
            this.price = recalculatePrice(this.price);
            System.out.println("���� �������� ����: " + this.price);
            smartToString(this.isSmart);
        } else if (st.equals("n")) {
            System.out.println("�� � �����, ��� ��� ���");
        } else {
            System.out.println("� ��������� ��� ������� ������������, ����� �� �����");
        }
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = recalculatePrice(price);

    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        this.isSmart = smart;
        smartToString(smart);
    }

    private void smartToString(boolean isSmart) {
        if (isSmart) {
            this.isSmartButString = ", ����� �� ";
        } else {
            this.isSmartButString = " ";
        }
    }

    private double recalculatePrice(double price) {
        if (this.discount != 0) {
            if (this.discount > 100) {
                System.out.println("������ ����� � ���� ����� ��� ������ ��, ��� ������ ����������. ���� ������ ����� 0%");
                this.discount = 0;
            }
            return price - (price / 100 * this.discount);
        }
        return price;
    }

    @Override
    public String toString() {
        return "TV <" +
                " ������ '" + model + '\'' +
                ", ��������� ���� " + price + " �." +
                ", ������ ����������: " + discount + "%" +
                isSmartButString +
                '>';
    }
}
