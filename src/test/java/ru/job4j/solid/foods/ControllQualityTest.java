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
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() + 1, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()), 100);
        Food milk2 = new Food("milk2",
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 2, LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 2, LocalDate.now().getDayOfMonth()), 100);
        Food milk3 = new Food("mil3",
                LocalDate.of(LocalDate.now().getYear() - 2, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue() + 2, LocalDate.now().getDayOfMonth()), 100);
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
                LocalDate.of(LocalDate.now().getYear() - 2, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue() + 2, LocalDate.now().getDayOfMonth()), 100);
        Food milk2 = new Food("milk2",
                LocalDate.of(LocalDate.now().getYear() - 2, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue() + 2, LocalDate.now().getDayOfMonth()), 100);
        Food milk3 = new Food("mil3",
                LocalDate.of(LocalDate.now().getYear() - 2, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue() + 2, LocalDate.now().getDayOfMonth()), 100);
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

    @Test(expected = IllegalArgumentException.class)
    public void whenException() {
        Food milk1 = new Food("milk1",
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()), 100);
    }

    /**
     * продукиы полежали и вышел срок годности, resort должен доставь все продукты из хранилищ, очистить освобождаемые хранилища
     * и заново запустить сортировку по хранилищам. Ожидаемое поведение - отправить все в Trash
     */
    @Test
    public void whenResort() {
        Food milk1 = new Food("milk1",
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() + 3, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()), 100);
        Food milk2 = new Food("milk2",
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue() - 2, LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue() + 2, LocalDate.now().getDayOfMonth()), 100);
        Food milk3 = new Food("mil3",
                LocalDate.of(LocalDate.now().getYear() - 2, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()),
                LocalDate.of(LocalDate.now().getYear() - 1, LocalDate.now().getMonthValue() + 2, LocalDate.now().getDayOfMonth()), 100);
        List<Food> foods = List.of(milk1, milk2, milk3);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        List<Store> stores = List.of(
                warehouse, shop, trash
        );
        ControllQuality control = new ControllQuality(stores);
        control.sort(foods);
        warehouse.getData().get(0).setExpiryDate(LocalDate.of(LocalDate.now().getYear() - 1,
                LocalDate.now().getMonthValue() + 1, LocalDate.now().getDayOfMonth()));
        shop.getData().get(0).setExpiryDate(LocalDate.of(LocalDate.now().getYear() - 1,
                LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()));

        trash.getData().get(0).setExpiryDate(LocalDate.of(LocalDate.now().getYear() - 1,
                LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth()));
        control.reSort();
        assertThat(trash.getData().get(0), is(milk1));
        assertThat(trash.getData().get(1), is(milk2));
        assertThat(trash.getData().get(2), is(milk3));
    }
}