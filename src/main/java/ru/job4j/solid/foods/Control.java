package ru.job4j.solid.foods;

import java.util.List;

public interface Control {
    void sort(Food food);
    void sort(List<Food> foods);
    void reSort();
}
