package oop.kurs2.shop;


import oop.kurs2.shop.services.ShopWorkService;

public class Main {

    public static void main(String[] args) {
        ShopWorkService work = new ShopWorkService();
        work.start();
    }
}
