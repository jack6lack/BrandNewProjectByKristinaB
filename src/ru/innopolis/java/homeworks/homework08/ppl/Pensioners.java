package ru.innopolis.java.homeworks.homework08.ppl;

public class Pensioners extends Person {

    public Pensioners(String name, double cash, char gender, int age) {
        super(name, cash, gender, age);
        checkAge(age);
    }

    @Override
    public double getCash() {
        if (super.getCash() < 0) {
            System.out.println("баланс не может быть отрицательным");
        }
        return super.getCash();
    }

    private void checkAge(int age) {
        if (age < 65) {
            throw new IllegalArgumentException("указан некорректный возраст");
        }
    }

}
