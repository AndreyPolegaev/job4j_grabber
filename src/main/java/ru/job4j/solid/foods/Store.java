package ru.job4j.solid.foods;

import java.util.List;

/**
 * describes methods for our stores
 */

public interface Store {

    void add(Food food);

    List<Food> getData();

    void clear();

}
