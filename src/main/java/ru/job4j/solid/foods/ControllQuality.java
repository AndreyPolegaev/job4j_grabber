package ru.job4j.solid.foods;

import java.util.ArrayList;
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
            foods.forEach(this::sort);
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
        List<Food> food = new ArrayList<>();
        stores.forEach(el -> {
            food.addAll(el.getData());
            el.clear();
        });
        sort(food);
    }
}
