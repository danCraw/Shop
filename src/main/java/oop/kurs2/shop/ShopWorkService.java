package oop.kurs2.shop;

import oop.kurs2.shop.model.ProductLocation;
import oop.kurs2.shop.model.ProductsType;

import java.util.HashMap;
import java.util.Map;

public class ShopWorkService {

    private ShopWork shopWork = new ShopWork();
    private static ShopWorkService shopWorkService;

    public void start() {
        shopWorkService = this;
        for (int i = 0; i < shopWork.getWorkDays(); i++) {
            shopWork.setDay(i);
           spendWorkDay();
        }
    }

    private void spendWorkDay() {
        ShowService showService = new ShowService();
        EventsService events = new EventsService();
        System.out.println("Продукты на складе: " +
                showService.countProducts(ProductLocation.warehouse.getAmountProducts(), shopWork.getWarehousProducts()));
        System.out.println("Продукты в торговом зале: " +
                showService.countProducts(ProductLocation.shoppingRoom.getAmountProducts(), shopWork.getShopRoomProducts()));
        System.out.println("Цены :");
        ShowService.showPrices(shopWork.getWarehousProducts(), shopWork.getShopRoomProducts());
        events.startRandomEvent();
    }

    public int getDay() {
        return shopWork.getDay();
    }

    public static ShopWorkService getShopWork() {
        return shopWorkService;
    }
}
