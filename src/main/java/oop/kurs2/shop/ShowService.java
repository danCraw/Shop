package oop.kurs2.shop;

import oop.kurs2.shop.model.ProductLocation;
import oop.kurs2.shop.model.ProductsType;

import java.util.*;

public class ShowService {

    public  Map<ProductsType, Integer> countProducts(LinkedList<ProductsType> place, Map<ProductsType, Integer> placeMap) {
        initPlace(place, placeMap);
        count(place, placeMap);
        return placeMap;
    }

    @SafeVarargs
    public static void showPrices(Map<ProductsType, Integer>... placeMaps) {
        Set<ProductsType> productsTypesInShop = new HashSet<>();
        initProducts(productsTypesInShop, placeMaps);
        for (ProductsType productsType : productsTypesInShop) {
            System.out.println(productsType + " " + productsType.getPrice());
        }
    }

    @SafeVarargs
    private static void initProducts(Set<ProductsType> productsTypesInShop, Map<ProductsType, Integer>... placeMap) {
        for (int i = 0; i < placeMap.length; i++) {
            productsTypesInShop.addAll(placeMap[i].keySet());
        }
    }

    private void count(LinkedList<ProductsType> location, Map<ProductsType, Integer> locationMap) {
        for (ProductsType productsType : location) {
            if (locationMap.containsKey(productsType)) {
                locationMap.put(productsType, locationMap.get(productsType) + 1);
            }

        }
    }

    private void initPlace(LinkedList<ProductsType> place, Map<ProductsType, Integer> placeProducts) {
        for (ProductsType productsType : place) {
            placeProducts.put(productsType, 0);
        }
    }
}
