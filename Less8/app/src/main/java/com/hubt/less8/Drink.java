package com.hubt.less8;

public class Drink {
    private String name;
    private int price;
    private int image;
    private int quantity;

    public Drink(String name, int price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
