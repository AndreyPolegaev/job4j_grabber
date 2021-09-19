package ru.job4j.solid.foods;

import java.util.ArrayList;
import java.util.List;

/**
 * store
 */

public class Trash implements  Store {

    private  List<Food> goods = new ArrayList<>();

    public Trash() {

    }

    @Override
    public boolean accept(Food food) {
        DateCompare dc = new DateCompare();
        float percent = dc.dateCompare(food.getCreateDate(), food.getExpiryDate());
        if (percent >= 100.0) {
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
