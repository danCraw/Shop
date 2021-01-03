package oop.kurs2.shop;

import oop.kurs2.shop.model.ProductLocation;
import oop.kurs2.shop.model.ProductsType;

import java.util.*;

public class EventsService {

    public void startRandomEvent() {
        ShowService showService = new ShowService();
        ShopWorkService shopWorkService = ShopWorkService.getShopWork();
        RandomService random = new RandomService();
        int event = random.fromTo(0, 4);
        System.out.println("День " + (shopWorkService.getDay() + 1));
        if (shopWorkService.getDay() == 0) {
            int amountProducts = random.fromTo(5, 100);
            ProductsType randomProduct = getRandomProduct();
            System.out.println("Завоз продуктов");
            System.out.println(amountProducts + " " + randomProduct);
            deliveryOfProduct(amountProducts, randomProduct);
            System.out.println();
        } else {
            if (shopWorkService.getDay() == 1) {
                int amountProducts = random.fromTo(5, ProductLocation.warehouse.getAmountProducts().size());
                System.out.println("Перевод продуков в тороговый зал");
                System.out.println(amountProducts + " товаров");
                productTranslation(amountProducts);
                System.out.println();
            } else {
                switch (event) {
                    case 0:
                        int amountProducts = random.fromTo(5, 100);
                        ProductsType randomProduct = getRandomProduct();
                        System.out.println("Завоз продуктов");
                        System.out.println(amountProducts + " " + randomProduct);
                        deliveryOfProduct(amountProducts, randomProduct);
                        System.out.println();
                        break;
                    case 1 :
                        if (ProductLocation.warehouse.getAmountProducts().size() > 0) {
                            amountProducts = random.fromTo(1, ProductLocation.warehouse.getAmountProducts().size());
                            System.out.println("Перевод продуков в тороговый зал");
                            System.out.println(amountProducts + " товаров");
                            productTranslation(amountProducts);
                            System.out.println();
                        }
                        break;
                    case 2:
                        System.out.println("Утилизация просрочки");
                        System.out.println("На складе уничтожено:" +
                                showService.countProducts((destroyProducts(ProductLocation.warehouse.getAmountProducts(), shopWorkService)),
                                        new HashMap<>()));
                        System.out.println("В торговом зале уничтожено:" +
                                showService.countProducts((destroyProducts(ProductLocation.shoppingRoom.getAmountProducts(), shopWorkService)),
                                        new HashMap<>()));
                        System.out.println();
                        break;
                    case 3:
                        System.out.println("Покупка продуктов");
                        System.out.print("Куплено :");
                        System.out.println(showService.countProducts(buyProducts(random.fromTo(0, ProductLocation.shoppingRoom.getAmountProducts().size())), new HashMap<>()));
                        System.out.println();
                        break;
                    case 4:
                        randomProduct = getRandomProduct();
                        int randomDiscount = random.fromTo(5, 90);
                        System.out.println("Установка скидки");
                        System.out.println("Скидка " + randomDiscount + "% на товары типа " + randomProduct);
                        discountSetting(randomDiscount, randomProduct);
                        System.out.println();
                        break;
                }
            }
        }
    }


    public void deliveryOfProduct(int amountProducts, ProductsType productsType) {
        for (int i = 0; i < amountProducts; i++) {
            ProductLocation.warehouse.getAmountProducts().add(productsType);
        }
    }

    public void productTranslation(int amountProducts) {
        Random random = new Random();
        for (int i = 0; i < amountProducts; i++) {
            int randomProduct = random.nextInt(ProductLocation.warehouse.getAmountProducts().size());
            ProductLocation.shoppingRoom.getAmountProducts().add(ProductLocation.warehouse.getAmountProducts().get(randomProduct));
            ProductLocation.warehouse.getAmountProducts().remove(randomProduct);
        }
    }

    public LinkedList<ProductsType> destroyProducts(LinkedList<ProductsType> place,  ShopWorkService shopWorkService) {
        LinkedList<ProductsType> replacementProducts = new LinkedList<>();
        for (ProductsType productsType : place) {
            if (shopWorkService.getDay() > productsType.getShelfLife()) {
                replacementProducts.add(productsType);
            }
        }
        place.removeAll(replacementProducts);
        return replacementProducts;
    }

    public LinkedList<ProductsType> buyProducts(int amountPurchases) {
        RandomService random = new RandomService();
        LinkedList<ProductsType> boughtProducts = new LinkedList<>();
        for (int i = 0; i < amountPurchases; i++) {
            int rndProductIndex = random.fromTo(0, ProductLocation.shoppingRoom.getAmountProducts().size() - 1);
           boughtProducts.add(ProductLocation.shoppingRoom.getAmountProducts().get(rndProductIndex));
            ProductLocation.shoppingRoom.getAmountProducts().remove(rndProductIndex);
        }
        return boughtProducts;
    }

    public void discountSetting(int discountPercent, ProductsType productsType) {
        productsType.setDiscount(discountPercent);
    }

    public ProductsType getRandomProduct() {
        RandomService random = new RandomService();
        ArrayList<ProductsType> productsTypes = new ArrayList<>(Arrays.asList(ProductsType.values()));
        return productsTypes.get(random.fromTo(0, productsTypes.size() - 1));
    }
}
