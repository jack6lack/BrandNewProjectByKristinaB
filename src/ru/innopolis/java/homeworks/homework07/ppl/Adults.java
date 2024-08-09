package ru.innopolis.java.homeworks.homework07.ppl;

import java.util.Objects;

public class Adults extends Person {
    private Double creditCardLimit = 150000.0;

    public Adults(String name, Double cash, Character gender, Integer age) {
        super(name, cash, gender, age);
        checkAge(age);
    }

    private void checkAge(Integer age) {
        if (age < 18 || age > 65) {
            throw new IllegalArgumentException("указан некорректный возраст");
        }
    }

    public Double getCreditCardLimit() {
        return creditCardLimit;
    }

    @Override
    public Double getCash() {
        if (super.getCash() < 0) {
            creditCardLimit = creditCardLimit + super.getCash();
            return creditCardLimit;
        } else if (creditCardLimit != 150_000) {
            creditCardLimit = super.getCash();
            return creditCardLimit;
        } else {
            return super.getCash();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Adults adults)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(getCreditCardLimit(), adults.getCreditCardLimit()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCreditCardLimit());
    }

    @Override
    public String toString() {
        return (getCreditCardLimit() == 150_000 ? "" : "остаток по кредитной карте для " + super.getName() + " составляет: " + getCash() + "\n") + super.toString();
    }
}
