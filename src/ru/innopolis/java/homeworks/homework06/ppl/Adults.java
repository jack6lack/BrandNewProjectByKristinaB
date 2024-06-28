package ru.innopolis.java.homeworks.homework06.ppl;

public class Adults extends Person {
    private double creditDebt;
    private double cash;

    public Adults(String name, double cash, char gender, int age) {
        super(name, cash, gender, age);
        checkAge(age);
    }

    private void checkAge(int age) {
        if (age < 18 || age > 65) {
            throw new IllegalArgumentException("указан некорректный возраст");
        }
    }

    public double getCreditDebt() {
        return creditDebt;
    }

    @Override
    public double getCash() {
        if (super.getCash() < 0) {
            creditDebt = Math.abs(super.getCash());
            System.out.println("теперь придется заложить в ломбард жопу. \nдолг по кредитке: " + creditDebt);
            return creditDebt;
        } else {
            return super.getCash();
        }
    }
}
