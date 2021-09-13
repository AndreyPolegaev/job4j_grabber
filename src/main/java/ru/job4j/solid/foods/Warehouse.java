package ru.job4j.solid.foods;

import java.util.ArrayList;
import java.util.List;

/**
 * apply Singleton template
 */

public class Warehouse implements Store {

    private final List<Food> goods = new ArrayList<>();

    private static Warehouse instance = null;

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    private Warehouse() {

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
