package ru.innopolis.java.homeworks.homework06.support;

import ru.innopolis.java.homeworks.homework06.ppl.Pensioners;
import ru.innopolis.java.homeworks.homework06.ppl.Person;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DiscountAmountHandler {
    private LocalTime lt;
    private Person person;
    private double discountAmount;
    private List<Integer> happyHours = new ArrayList<>(Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));


    public DiscountAmountHandler(LocalTime lt, Person person) {
        this.lt = lt;
        this.person = person;
        this.discountAmount = discountCalculator();
    }

    private double discountCalculator() {
        if (isNeedsADiscount()) {
            return discountAmount = discountAmount + lt.getHour() + (person instanceof Pensioners ? 5 : 0);
        } else {
            return discountAmount = 0;
        }
    }

    private boolean isNeedsADiscount() {
        if (happyHours.contains(lt.getHour()) || person instanceof Pensioners) {
            return true;
        } else {
            return false;
        }
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
}