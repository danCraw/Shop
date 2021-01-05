package oop.kurs2.shop.services;

public class RandomService {
     public int fromTo(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
