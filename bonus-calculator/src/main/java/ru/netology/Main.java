package ru.netology;

public class Main {
    public static void main(String[] args) {
        BonusService service = new BonusService();

        long amount = 1000_60;
        boolean registered = true;

        long l = service.calculate(amount, registered);
        System.out.println(l);
    }
}