package oop.kurs2.shop.model;

import java.util.HashMap;
import java.util.Map;

public class ShopWork {
    private Map<ProductsType, Integer> warehousProducts = new HashMap<>();
    private Map<ProductsType, Integer> shopRoomProducts = new HashMap<>();
    private int day;
    private int workDays = 14;

    public Map<ProductsType, Integer> getWarehousProducts() {
        return warehousProducts;
    }

    public Map<ProductsType, Integer> getShopRoomProducts() {
        return shopRoomProducts;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public int getWorkDays() {
        return workDays;
    }
}
