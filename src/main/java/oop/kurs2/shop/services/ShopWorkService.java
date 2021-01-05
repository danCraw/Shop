package oop.kurs2.shop.services;

import oop.kurs2.shop.json.JSon;
import oop.kurs2.shop.model.ProductLocation;
import oop.kurs2.shop.model.ShopWork;

import java.io.IOException;

public class ShopWorkService {

    private ShopWork shopWork = new ShopWork();
    private static ShopWorkService shopWorkService;

    public void start() {
        JSon jSon = new JSon();
        shopWorkService = this;
        for (int i = 0; i <= shopWork.getWorkDays(); i++) {
            shopWork.setDay(i);
           spendWorkDay();
            writeToJson(jSon);
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

    public void writeToJson(JSon jSon) {
        try {
            jSon.serialize(shopWorkService, "shop.json");
            jSon.deserialize("shop.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDay() {
        return shopWork.getDay();
    }

    public static ShopWorkService getShopWork() {
        return shopWorkService;
    }
}
