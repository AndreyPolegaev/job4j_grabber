package ru.job4j.solid.foods;

import java.util.List;

/**
 * Automatically splits into stores
 */

public class ControllQuality implements Control {

    private final List<Store> stores;

    public ControllQuality(List<Store> stores) {
        this.stores = stores;
    }

    @Override
    public void sort(List<Food> foods) {
        for (Store temp : stores) {
            for (Food tempFood : foods) {
                if (temp.accept(tempFood)) {
                    temp.add(tempFood);
                }
            }
        }
    }

    @Override
    public void sort(Food food) {
        for (Store temp : stores) {
            if (temp.accept(food)) {
                temp.add(food);
            }
        }
    }

    @Override
    public void reSort() {
        for (Store temp : stores) {
            sort(temp.getData());
        }
    }
}
