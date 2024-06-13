package ru.innopolis.java.homeworks.homework05Addition;

public class Television {
    private String model;
    private double priceWithoutDiscount;
    private double finalPrice;
    private double discount;
    private boolean isSmart;
    private final String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //���� ���� �� ����� ����� �����
    public Television() {
    }

    //���� ���� �����
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
            System.out.println("������ ����� � ���� ����� ��� ������ ��, ��� ������ ����������. ���� ������ ����� 0%");
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
                " ������ '" + model + '\'' +
                ", ���� " + priceWithoutDiscount + " �." +
                ", ������ ����������: " + discount + "%" +
                ", ���� ��������� ���� ����������: " + getFinalPrice() + " p." +
                (isSmart ? ", ����� ��" : ", �� ����� ��") +
                '>';
    }
}
