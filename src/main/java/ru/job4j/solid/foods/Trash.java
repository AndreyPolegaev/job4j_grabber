package ru.job4j.solid.foods;

import java.util.ArrayList;
import java.util.List;

/**
 * apply Singleton template
 */

public class Trash implements  Store {

    private final List<Food> goods = new ArrayList<>();

    private static Trash instance = null;

    public static Trash getInstance() {
        if (instance == null) {
            instance = new Trash();
        }
        return instance;
    }

    private Trash() {

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
