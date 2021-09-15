package ru.job4j.solid.foods;

import java.util.ArrayList;
import java.util.List;

/**
 * store
 */

public class Shop implements Store {

    private final List<Food> goods = new ArrayList<>();

    public Shop() {

    }

    @Override
    public boolean accept(Food food) {
        DateCompare dc = new DateCompare();
        float percent = dc.dateCompare(food.getCreateDate(), food.getExpiryDate());
        if (percent >= 25.0 && percent < 75.0) {
            return true;
        } else if (percent >= 75.0 && percent < 100.0) {
            food.setDiscount(0.5f);
            return true;
        }
        return false;
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
