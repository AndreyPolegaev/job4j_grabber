package ru.job4j.solid.parking;

/**
 * грузовой автомобиль размер не меньше 2
 */

public class TruckCar implements Car {

    private int size;

    public TruckCar(int size) {
        this.size = size;
        checkSize();
    }

    @Override
    public void checkSize() {
        if (size < 2) {
            throw new IllegalArgumentException("Truck car's size cant be less then 2");
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
