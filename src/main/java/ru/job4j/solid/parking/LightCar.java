package ru.job4j.solid.parking;

/**
 * легковой автомобиль размер не больше 1
 */
public class LightCar implements Car {

    private int size;

    public LightCar(int size) {
        this.size = size;
        checkSize();
    }

    @Override
    public void checkSize() {
        if (size > 1) {
            throw new IllegalArgumentException("Light car's size cant be more then 1");
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
