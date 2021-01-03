package oop.kurs2.shop.model;

import oop.kurs2.shop.defaults.Defaults;

public enum ProductsType {
    meat( 270, 5, 0),
    bread( 35, 2, 0),
    milk(75, 5, 0),
    vegetables( 80,15, 0),
    fruits( 140,7, 0),
    chemistry( 200, Defaults.infinity, 0),
    grocery(  100, Defaults.infinity, 0),
    alcohol( 300, Defaults.infinity, 0);

    int price;
    int shelfLife;
    int discount;

    ProductsType(int price, int shelfLife, int discount) {

        this.price = price;
        this.shelfLife = shelfLife;
        this.discount = discount;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
        this.price -= (price / 100) * discount;
    }

    public int getPrice() {
        return price;
    }
}

