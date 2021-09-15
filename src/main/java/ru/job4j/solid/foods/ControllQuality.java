package ru.job4j.solid.foods;

import java.util.List;

/**
 * Automatically splits into stores
 */

public class ControllQuality implements Control {

    private final List<Store> stores;

    private final List<Food> foods;

    public ControllQuality(List<Store> stores, List<Food> foods) {
        this.stores = stores;
        this.foods = foods;
        exChange();
    }

    public void exChange() {
        for (Store temp : stores) {
            for (Food tempFood : foods) {
                if (temp.accept(tempFood)) {
                    temp.add(tempFood);
                }
            }
        }
    }
}
