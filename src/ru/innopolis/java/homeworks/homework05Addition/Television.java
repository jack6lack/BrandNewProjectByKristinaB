package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Arrays;
import java.util.Objects;

public class Television {
    private String model;
    private double priceWithoutDiscount;
    private double finalPrice;
    private double discount;
    private boolean isSmart;
    private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int channelNumber;
    private int volumeLevel;
    private boolean isTurnedOn;

    public Television(String model, double priceWithoutDiscount, double discount, boolean isSmart, int channelNumber, int volumeLevel, boolean isTurnedOn) {
        this.model = model;
        this.priceWithoutDiscount = priceWithoutDiscount;
        this.discount = discount;
        this.finalPrice = getFinalPrice();
        this.isSmart = isSmart;
        this.channelNumber = channelNumber;
        this.volumeLevel = volumeLevel;
        this.isTurnedOn = isTurnedOn;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public void setPriceWithoutDiscount(double priceWithoutDiscount) {
        this.priceWithoutDiscount = priceWithoutDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount > 100) {
            System.out.println("больше всего в этой жизни вас радует то, что воздух бесплатный. ваша скидка будет 0%");
            this.discount = 0;
        } else {
            this.discount = discount;
        }
    }

    public boolean isSmart() {
        return isSmart;
    }

    public void setSmart(boolean smart) {
        this.isSmart = smart;
    }

    public double getFinalPrice() {
        return this.priceWithoutDiscount - (this.priceWithoutDiscount / 100 * this.discount);
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public int getVolumeLevel() {
        return volumeLevel;
    }

    public void setVolumeLevel(int volumeLevel) {
        this.volumeLevel = volumeLevel;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    public void setTurnedOn(boolean turnedOn) {
        isTurnedOn = turnedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Television that)) return false;
        return Double.compare(priceWithoutDiscount, that.priceWithoutDiscount) == 0 && Double.compare(finalPrice, that.finalPrice) == 0 && Double.compare(discount, that.discount) == 0 && isSmart == that.isSmart && channelNumber == that.channelNumber && volumeLevel == that.volumeLevel && isTurnedOn == that.isTurnedOn && Objects.equals(model, that.model) && Objects.deepEquals(alphabet, that.alphabet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, priceWithoutDiscount, finalPrice, discount, isSmart, Arrays.hashCode(alphabet), channelNumber, volumeLevel, isTurnedOn);
    }

    @Override
    public String toString() {
        return "Television < " +
                " модель : '" + model + '\'' +
                ", цена : " + priceWithoutDiscount + " р." +
                ", скидка составляет: " + discount + "%" +
                ", ваша финальная цена составляет: " + getFinalPrice() + " p." +
                (isSmart ? ", смарт тв" : ", не смарт тв") +
                ", текущий канал: " + channelNumber +
                ", текущий уровень звука: " + volumeLevel +
                (isTurnedOn ? ", телевизор включен" : ", телевизор выключен") +
                ", hashCode: " + hashCode() +
                " > ";
    }
}
