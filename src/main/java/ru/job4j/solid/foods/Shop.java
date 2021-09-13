package ru.job4j.solid.foods;

import java.util.ArrayList;
import java.util.List;

/**
 * apply Singleton template
 */

public class Shop implements Store {

    private final List<Food> goods = new ArrayList<>();

    private static Shop instance = null;

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    private Shop() {

    }


    @Override
    public void add(Food food) {
        goods.add(food);
    }

    @Override
    public List<Food> getData() {
        return goods;
    }

    @Override
    public void clear() {
        goods.clear();
    }
}
