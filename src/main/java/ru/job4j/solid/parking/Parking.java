package ru.job4j.solid.parking;

import java.util.List;

/**
 * при реализации парковки, кол-во мест хранится в 2-х массивах.
 * перегруженный метод accept() принимает список машин или 1-у машину.
 * методы getFreePlaceLight(), getFreePlaceTruck() - возвращают свободные места на паркове.
 */

public interface Parking {

    boolean accept(List<Car> car);

    boolean accept(Car car);

    int getFreePlaceLight();

    int getFreePlaceTruck();
}
