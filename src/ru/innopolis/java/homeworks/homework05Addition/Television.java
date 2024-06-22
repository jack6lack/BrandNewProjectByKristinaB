package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Arrays;
import java.util.Objects;

public class Television {
    private final String model;
    private double priceWithoutDiscount;
    private double discount;
    private boolean isSmart;
    private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int volumeLevel;
    private boolean isTurnedOn;
    private Channel[] channels;
    private int currentChannel = 0;

    public Television(String model, double priceWithoutDiscount, double discount, boolean isSmart, int volumeLevel, boolean isTurnedOn, int currentChannel) {
        this.model = model;
        this.priceWithoutDiscount = priceWithoutDiscount;
        this.discount = discount;
        getFinalPrice();
        this.isSmart = isSmart;
        this.volumeLevel = volumeLevel;
        this.isTurnedOn = isTurnedOn;
        this.currentChannel = currentChannel;
    }

    public Television(String model, int volumeLevel, boolean isTurnedOn, Channel[] channels) {
        this.model = model;
        this.volumeLevel = volumeLevel;
        this.isTurnedOn = isTurnedOn;
        this.channels = channels;
    }

    public int getCurrentChannel() {
        return currentChannel;
    }

    public Channel[] getChannels() {
        return channels;
    }

    public void goToSelectedChannel(int number) {
        if (number > channels.length) {
            System.out.println("такого канала не существует");
        } else {
            System.out.println("переключили на канал \"" + channels[number - 1].channelName() + "\", текущая программа: " + channels[number - 1].program());
            currentChannel = number;
        }
    }

    public String getModel() {
        return model;
    }

    public double getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public double getFinalPrice() {
        return this.priceWithoutDiscount - (this.priceWithoutDiscount / 100 * this.discount);
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public int getVolumeLevel() {
        return volumeLevel;
    }

    public boolean isTurnedOn() {
        return isTurnedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Television that)) return false;
        return Double.compare(getPriceWithoutDiscount(), that.getPriceWithoutDiscount()) == 0 && Double.compare(getFinalPrice(), that.getFinalPrice()) == 0 && Double.compare(getDiscount(), that.getDiscount()) == 0 && isSmart() == that.isSmart() && getVolumeLevel() == that.getVolumeLevel() && isTurnedOn() == that.isTurnedOn() && getCurrentChannel() == that.getCurrentChannel() && Objects.equals(getModel(), that.getModel()) && Objects.deepEquals(getAlphabet(), that.getAlphabet()) && Objects.deepEquals(getChannels(), that.getChannels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getPriceWithoutDiscount(), getFinalPrice(), getDiscount(), isSmart(), Arrays.hashCode(getAlphabet()), getVolumeLevel(), isTurnedOn(), Arrays.hashCode(getChannels()), getCurrentChannel());
    }

    @Override
    public String toString() {
        return "Television < " +
                " модель : '" + model + '\'' +
                ", цена : " + priceWithoutDiscount + " р." +
                ", скидка составляет: " + discount + "%" +
                ", ваша финальная цена составляет: " + getFinalPrice() + " p." +
                (isSmart ? ", смарт тв" : ", не смарт тв") +
                ", текущий канал: " + currentChannel +
                ", текущий уровень звука: " + volumeLevel +
                (isTurnedOn ? ", телевизор включен" : ", телевизор выключен") +
                " > ";
    }
}
