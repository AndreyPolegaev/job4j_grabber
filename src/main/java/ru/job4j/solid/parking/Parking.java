package ru.job4j.solid.parking;

/**
 * при реализации парковки, кол-во мест хранится в 2-х массивах типа int[]
 * int[] light = new int[...]
 * int[] truck = new int[...]
 */

public interface Parking {

    boolean accept(Car car);

    int getFreePlace();
}
