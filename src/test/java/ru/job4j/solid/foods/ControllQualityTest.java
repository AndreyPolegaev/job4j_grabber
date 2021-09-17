package ru.job4j.solid.foods;

import org.junit.Ignore;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Ignore
    @Test
    public void whenTestDateParserThenForShop() {
        DateCompare dateCompare = new DateCompare();
        float value = dateCompare.dateCompare(
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2021, 9, 30));
        assertTrue(value > 25.0 && value < 100);
    }

    @Ignore
    @Test
    public void whenTestDateParserThenForWarehouse() {
        DateCompare dateCompare = new DateCompare();
        float value = dateCompare.dateCompare(
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2022, 9, 30));
        assertTrue(value < 25.0);
    }

    @Ignore
    @Test
    public void whenTestDateParserThenForTrash() {
        DateCompare dateCompare = new DateCompare();
        float value = dateCompare.dateCompare(
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2021, 9, 10));
        assertTrue(value >= 100.0);
    }


    @Test
    public void whenGoodsDividedIntoThreeStore() {
        Food milk1 = new Food("milk1",
                LocalDate.of(2021, 9, 10),
                LocalDate.of(2022, 5, 15), 100);
        Food milk2 = new Food("milk2",
                LocalDate.of(2021, 8, 16),
                LocalDate.of(2021, 10, 15), 80);
        Food milk3 = new Food("mil3",
                LocalDate.of(2020, 12, 1),
                LocalDate.of(2021, 9, 10), 50);

        List<Food> foods = List.of(milk1, milk2, milk3);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(
                warehouse, shop, trash
        );
        ControllQuality control = new ControllQuality(stores);
        control.sort(foods);
        assertThat(warehouse.getData().get(0), is(milk1));
        assertThat(shop.getData().get(0), is(milk2));
        assertThat(trash.getData().get(0), is(milk3));
    }

    @Test
    public void whenGoodsDividedIntoOneStore() {
        Food milk1 = new Food("milk1",
                LocalDate.of(2020, 9, 10),
                LocalDate.of(2021, 1, 1), 100);
        Food milk2 = new Food("milk2",
                LocalDate.of(2020, 8, 16),
                LocalDate.of(2021, 1, 1), 80);
        Food milk3 = new Food("mil3",
                LocalDate.of(2020, 12, 1),
                LocalDate.of(2021, 1, 1), 50);

        List<Food> foods = List.of(milk1, milk2, milk3);

        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(
                warehouse, shop, trash
        );
        ControllQuality control = new ControllQuality(stores);
        control.sort(foods);
        assertThat(trash.getData().get(0), is(milk1));
        assertThat(trash.getData().get(1), is(milk2));
        assertThat(trash.getData().get(2), is(milk3));
        assertTrue(warehouse.getData().isEmpty());
        assertTrue(shop.getData().isEmpty());
    }
}