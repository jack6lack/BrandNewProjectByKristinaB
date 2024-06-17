package ru.innopolis.java.homeworks.homework03;

public class Television {
    private String model;
    private double priceWithoutDiscount;
    private double finalPrice;
    private double discount;
    private boolean isSmart;
    private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //если юзер не знает какой телек
    public Television() {
    }

    //если юзер знает
    public Television(String model, double priceWithoutDiscount, double discount, boolean isSmart) {
        this.model = model;
        this.discount = discount;
        this.priceWithoutDiscount = priceWithoutDiscount;
        this.isSmart = isSmart;
        this.finalPrice = getFinalPrice();
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

    @Override
    public String toString() {
        return "TV <" +
                " модель '" + model + '\'' +
                ", цена " + priceWithoutDiscount + " р." +
                ", скидка составляет: " + discount + "%" +
                ", ваша финальная цена составляет: " + getFinalPrice() + " p." +
                (isSmart ? ", смарт тв" : ", не смарт тв") +
                '>';
    }
}
