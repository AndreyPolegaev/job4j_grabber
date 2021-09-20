package ru.job4j.solid.parking;

/**
 * легковой автомобиль размер не больше 1
 */
public class LightCar extends Car {

    private int size;

    public LightCar() {
        this.size = 1;
    }

    public int getSize() {
        return size;
    }
}
