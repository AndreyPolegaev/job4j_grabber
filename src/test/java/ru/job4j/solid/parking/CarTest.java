package ru.job4j.solid.parking;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CarTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenException() {
        Parking park = new Park(new Car[0], new Car[1]);
        Car carLTruck1 = new TruckCar(1);
        park.accept(List.of(carLTruck1));
        assertTrue(true);
    }

    @Test
    public void whenAddThreeLightCars() {
        Parking park = new Park(new Car[5], new Car[2]);
        Car carLight1 = new LightCar();
        Car carLight2 = new LightCar();
        Car carLight3 = new LightCar();
        park.accept(List.of(carLight1, carLight2, carLight3));
        assertThat(park.getFreePlaceLight(), is(2));
        assertThat(park.getFreePlaceTruck(), is(2));
    }

    @Test
    public void whenAddThreeLightCarsAndOneTruck() {
        Parking park = new Park(new Car[5], new Car[2]);
        Car carLight1 = new LightCar();
        Car carLight2 = new LightCar();
        Car carLight3 = new LightCar();
        Car carLTruck = new TruckCar(2);
        park.accept(List.of(carLight1, carLight2, carLight3, carLTruck));
        assertThat(park.getFreePlaceLight(), is(2));
        assertThat(park.getFreePlaceTruck(), is(1));
    }

    @Test
    public void whenAddThreeLightCarsAndOneTruck3() {
        Parking park = new Park(new Car[6], new Car[2]);
        Car carLight1 = new LightCar();
        Car carLight2 = new LightCar();
        Car carLight3 = new LightCar();
        Car carLTruck = new TruckCar(3);
        park.accept(List.of(carLight1, carLight2, carLight3, carLTruck));
        assertThat(park.getFreePlaceLight(), is(3));
        assertThat(park.getFreePlaceTruck(), is(1));
    }

    @Test
    public void whenAddOneTruck() {
        Parking park = new Park(new Car[4], new Car[3]);
        Car carLTruck = new TruckCar(4);
        park.accept(List.of(carLTruck));
        assertThat(park.getFreePlaceLight(), is(4));
        assertThat(park.getFreePlaceTruck(), is(2));
    }

    @Test
    public void whenAddTwoLightAndTwoTruck() {
        Parking park = new Park(new Car[4], new Car[5]);
        Car carLight1 = new LightCar();
        Car carLight2 = new LightCar();
        Car carLTruck1 = new TruckCar(4);
        Car carLTruck2 = new TruckCar(2);
        park.accept(List.of(carLight1, carLight2, carLTruck1, carLTruck2));
        assertThat(park.getFreePlaceLight(), is(2));
        assertThat(park.getFreePlaceTruck(), is(3));
    }

    @Test
    public void whenAddOnlyTruck() {
        Parking park = new Park(new Car[5], new Car[5]);
        Car carLTruck1 = new TruckCar(2);
        Car carLTruck2 = new TruckCar(4);
        park.accept(List.of(carLTruck1, carLTruck2));
        assertThat(park.getFreePlaceLight(), is(5));
        assertThat(park.getFreePlaceTruck(), is(3));
    }

    @Test
    public void whenAddListAndSeparatelyCar() {
        Parking park = new Park(new Car[2], new Car[5]);
        Car carLight1 = new LightCar();
        Car carLTruck2 = new TruckCar(4);
        Car carLight2 = new LightCar();
        park.accept(List.of(carLight1, carLTruck2));
        park.accept(carLight2);
        assertThat(park.getFreePlaceLight(), is(0));
        assertThat(park.getFreePlaceTruck(), is(4));
    }

    @Test
    public void whenLightPlacesIsFinished() {
        Parking park = new Park(new Car[2], new Car[5]);
        Car carLight1 = new LightCar();
        Car carLight2 = new LightCar();
        Car carLight3 = new LightCar();
        park.accept(List.of(carLight1, carLight2));
        assertThat(park.getFreePlaceLight(), is(0));
        assertThat(park.getFreePlaceTruck(), is(5));
        assertFalse(park.accept(carLight3));
    }

    @Test
    public void whenTruckPlacesIsFinished() {
        Parking park = new Park(new Car[0], new Car[1]);
        Car carLTruck1 = new TruckCar(2);
        Car carLTruck2 = new TruckCar(4);
        park.accept(List.of(carLTruck1));
        assertThat(park.getFreePlaceLight(), is(0));
        assertThat(park.getFreePlaceTruck(), is(0));
        assertFalse(park.accept(carLTruck2));
    }
}