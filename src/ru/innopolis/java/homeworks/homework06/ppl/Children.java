package ru.innopolis.java.homeworks.homework06.ppl;

public class Children extends Person {
    private boolean ableToBuy;


    public Children(String name, double cash, char gender, int age) {
        super(name, cash, gender, age);
        checkAge(age);
    }

    @Override
    public double getCash() {
        if (ableToBuy) {
            return super.getCash();
        } else {
            System.out.println("у этого персонажа нет денег");
            return 0;
        }
    }

    private void checkAge(int age) {
        if (age < 0 || age > 17) {
            throw new IllegalArgumentException("указан некорректный возраст");
        } else if (age < 6) {
            System.out.println("данный персонаж не имеет возможности делать покупки");
            ableToBuy = false;
        } else {
            ableToBuy = true;
        }
    }

    public boolean isAbleToBuy() {
        return ableToBuy;
    }
}
