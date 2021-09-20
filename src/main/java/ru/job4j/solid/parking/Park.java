package ru.job4j.solid.parking;

import java.util.List;

public class Park implements Parking {

    private final Car[] light;
    private final Car[] truck;
    private int countLight = 0;
    private int countTruck = 0;

    public Park(Car[] light, Car[] truck) {
        this.light = light;
        this.truck = truck;
    }

    @Override
    public void accept(List<Car> car) {
       car.forEach(this::accept);
    }

    @Override
    public boolean accept(Car car) {
        boolean answer = false;
        if (car.getSize() == 1 && light.length - countLight > 0) {
            light[countLight++] = car;
            answer = true;
        } else if (car.getSize() > 1 && truck.length - countTruck > 0) {
            truck[countTruck++] = car;
            answer = true;
        } else if (car.getSize() > 1 && checkLightPlace(car.getSize())) {
            for (int i = 0; i < car.getSize(); i++) {
                light[countLight++] = car;
            }
            answer = true;
        }
        return answer;
    }

    private boolean checkLightPlace(int size) {
        int count = 0;
        boolean checkFreePlace = false;
        for (int i = 0; i < light.length - 1; i++) {
            if (count == size) {
                break;
            }
            checkFreePlace = false;
            if (light[i] == null) {
                for (int j = 0; j < size; j++) {
                    if (light[i + j] == null) {
                        checkFreePlace = true;
                        count++;
                    }
                }
            }
        }
        return checkFreePlace;
    }

    @Override
    public int getFreePlaceLight() {
        return light.length - countLight;
    }

    @Override
    public int getFreePlaceTruck() {
        return truck.length - countTruck;
    }
}
