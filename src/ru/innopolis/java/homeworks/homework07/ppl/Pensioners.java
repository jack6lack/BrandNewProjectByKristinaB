package ru.innopolis.java.homeworks.homework07.ppl;

public class Pensioners extends Person {

    public Pensioners(String name, Double cash, Character gender, Integer age) {
        super(name, cash, gender, age);
        checkAge(age);
    }

    @Override
    public Double getCash() {
        if (super.getCash() < 0) {
            System.out.println("баланс не может быть отрицательным");
        }
        return super.getCash();
    }

    private void checkAge(Integer age) {
        if (age < 65) {
            throw new IllegalArgumentException("указан некорректный возраст");
        }
    }

}
