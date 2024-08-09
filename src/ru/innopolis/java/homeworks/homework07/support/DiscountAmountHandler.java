package ru.innopolis.java.homeworks.homework07.support;

import ru.innopolis.java.homeworks.homework07.ppl.Pensioners;
import ru.innopolis.java.homeworks.homework07.ppl.Person;
import ru.innopolis.java.homeworks.homework07.product.DiscountProduct;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DiscountAmountHandler {
    private LocalTime lt;
    private Person person;
    private Integer discountAmount = 0;
    private List<Integer> happyHours = new ArrayList<>(Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));
    private DiscountProduct discountProduct;


    public DiscountAmountHandler(LocalTime lt, Person person, DiscountProduct discountProduct) {
        this.lt = lt;
        this.person = person;
        this.discountProduct = discountProduct;
        this.discountAmount = discountCalculator();
    }

    private Integer discountCalculator() {
        if (discountAmount == 0) {
            discountAmount = discountProduct.getDiscount()
                    + (happyHours.contains(lt.getHour()) ? lt.getHour() : 0)
                    + (person instanceof Pensioners ? 5 : 0);
            return discountAmount;
        } else {
            return discountAmount;
        }
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountAmountHandler that)) return false;
        return Objects.equals(lt, that.lt) && Objects.equals(person, that.person) && Objects.equals(discountAmount, that.discountAmount) && Objects.equals(happyHours, that.happyHours) && Objects.equals(discountProduct, that.discountProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lt, person, discountAmount, happyHours, discountProduct);
    }
}