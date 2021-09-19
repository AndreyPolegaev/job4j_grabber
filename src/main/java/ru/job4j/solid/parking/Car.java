package ru.job4j.solid.parking;

/**
 * метод checkSize() - реализует полиморфизм на размер автомобилей.
 */

public interface Car {

    void checkSize();

    int getSize();
}
