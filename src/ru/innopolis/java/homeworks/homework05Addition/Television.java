package ru.innopolis.java.homeworks.homework05Addition;

import java.util.Arrays;
import java.util.Objects;

public class Television {
    private final String model;
    private Double priceWithoutDiscount;
    private Integer discount;
    private Boolean isSmart;
    private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private Integer volumeLevel;
    private Boolean isTurnedOn;
    private Channel[] channels;
    private Integer currentChannel = 0;

    public Television(String model, Double priceWithoutDiscount, Integer discount, Boolean isSmart, Integer volumeLevel, Boolean isTurnedOn, Integer currentChannel) {
        this.model = model;
        this.priceWithoutDiscount = priceWithoutDiscount;
        this.discount = discount;
        getFinalPrice();
        this.isSmart = isSmart;
        this.volumeLevel = volumeLevel;
        this.isTurnedOn = isTurnedOn;
        this.currentChannel = currentChannel;
    }

    public Television(String model, Integer volumeLevel, Boolean isTurnedOn, Channel[] channels) {
        this.model = model;
        this.volumeLevel = volumeLevel;
        this.isTurnedOn = isTurnedOn;
        this.channels = channels;
    }

    public Integer getCurrentChannel() {
        return currentChannel;
    }

    public Channel[] getChannels() {
        return channels;
    }

    public void goToSelectedChannel(Integer number) {
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

    public Double getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Boolean isSmart() {
        return isSmart;
    }

    public Double getFinalPrice() {
        return this.priceWithoutDiscount - (this.priceWithoutDiscount / 100 * this.discount);
    }

    public String[] getAlphabet() {
        return alphabet;
    }

    public Integer getVolumeLevel() {
        return volumeLevel;
    }

    public Boolean isTurnedOn() {
        return isTurnedOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Television that)) return false;
        return Objects.equals(getModel(), that.getModel()) && Objects.equals(getPriceWithoutDiscount(), that.getPriceWithoutDiscount()) && Objects.equals(getDiscount(), that.getDiscount()) && Objects.equals(isSmart, that.isSmart) && Objects.deepEquals(getAlphabet(), that.getAlphabet()) && Objects.equals(getVolumeLevel(), that.getVolumeLevel()) && Objects.equals(isTurnedOn, that.isTurnedOn) && Objects.deepEquals(getChannels(), that.getChannels()) && Objects.equals(getCurrentChannel(), that.getCurrentChannel());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getPriceWithoutDiscount(), getDiscount(), isSmart, Arrays.hashCode(getAlphabet()), getVolumeLevel(), isTurnedOn, Arrays.hashCode(getChannels()), getCurrentChannel());
    }

    @Override
    public String toString() {
        return "Television{" +
                "model='" + model + '\'' +
                ", priceWithoutDiscount=" + priceWithoutDiscount +
                ", discount=" + discount +
                ", isSmart=" + isSmart +
                ", volumeLevel=" + volumeLevel +
                ", isTurnedOn=" + isTurnedOn +
                ", channels=" + Arrays.toString(channels) +
                ", currentChannel=" + currentChannel +
                '}';
    }
}
