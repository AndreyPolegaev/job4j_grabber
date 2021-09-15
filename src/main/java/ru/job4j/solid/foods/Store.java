package ru.job4j.solid.foods;

import java.util.List;

/**
 * describes methods for our stores
 */

public interface Store {

    boolean accept(Food food);

    void add(Food food);

    List<Food> getData();

    void clear();

}
