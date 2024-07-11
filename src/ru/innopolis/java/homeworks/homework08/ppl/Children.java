package ru.innopolis.java.homeworks.homework08.ppl;

import java.util.Objects;

public class Children extends Person {
    private boolean ableToBuy;


    public Children(String name, double cash, char gender, int age) {
        super(name, cash, gender, age);
        checkAge(age);
    }

    @Override
    public double getCash() {
        if (ableToBuy) {
            if (super.getCash() < 0) {
                System.out.println("баланс не может быть отрицательным");
            }
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
            ableToBuy = false;
        } else {
            ableToBuy = true;
        }
    }

    public boolean isAbleToBuy() {
        return ableToBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Children children)) return false;
        if (!super.equals(o)) return false;
        return isAbleToBuy() == children.isAbleToBuy();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isAbleToBuy());
    }

    @Override
    public String toString() {
        return (ableToBuy ? "" : "'" + super.getName() + "' не имеет возможности делать покупки\n") + super.toString();
    }
}
