package ru.job4j.solid.foods;

import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ControllQualityTest {

    @Test
    public void whenAddToTheWarehouse() {
        Food milk = new Food(
                "Milk Parmalat",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2022, 9, 30),
                100);
        Control cq = new ControllQuality();
        Store expected = cq.exChange(milk);
        Store shop = Warehouse.getInstance();
        assertThat(expected.getData().get(0), is(shop.getData().get(0)));
        assertThat(expected.getData().get(0).getPrice(), is(100.0F));
        shop.clear();
    }

    @Test
    public void whenAddToTheShop() {
        Food milk = new Food(
                "Milk Parmalat",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2021, 10, 1),
                100);
        Control cq = new ControllQuality();
        Store expected = cq.exChange(milk);
        Store shop = Shop.getInstance();
        assertThat(expected.getData().get(0), is(shop.getData().get(0)));
        assertThat(expected.getData().get(0).getPrice(), is(100.0F));
        shop.clear();

    }

    @Test
    public void whenAddToTheShopWithDiscount() {
        Food milk = new Food(
                "Milk Parmalat",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2021, 9, 16),
                100);
        Control cq = new ControllQuality();
        Store expected = cq.exChange(milk);
        Store shop = Shop.getInstance();
        assertThat(expected.getData().get(0), is(shop.getData().get(0)));
        assertThat(expected.getData().get(0).getPrice(), is(50.0F));
        shop.clear();
    }

    @Test
    public void whenAddToTheTrash() {
        Food milk = new Food(
                "Milk Parmalat",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2021, 9, 10),
                100);
        Control cq = new ControllQuality();
        Store expected = cq.exChange(milk);
        Store trash = Trash.getInstance();
        assertThat(expected.getData().get(0), is(trash.getData().get(0)));
        trash.clear();
    }


    /**
     * Check that the good added in the wrong store
     * Actually added in the Warehouse, test trash
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSeveralProducts() {
        Food milk = new Food(
                "Milk Parmalat",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2022, 10, 10),
                100);
        Control cq = new ControllQuality();
        Store expected = cq.exChange(milk);
        List<Food> goods = expected.getData();
        Trash trash = Trash.getInstance();
        assertThat(goods.get(0), is(trash.getData().get(0)));
        trash.clear();
    }

    @Test
    public void whenAddASeveralProducts() {
        Food milkTrash = new Food(
                "Milk 1",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2021, 9, 5),
                100);

        Food milkWarehouse = new Food(
                "Milk 2",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2022, 9, 25),
                110);

        Food milkShop = new Food(
                "Milk 3",
                LocalDate.of(2021, 9, 1),
                LocalDate.of(2021, 9, 20),
                110);
        Control cq = new ControllQuality();
        Store expected1 = cq.exChange(milkTrash);
        Store expected2 = cq.exChange(milkWarehouse);
        Store expected3 = cq.exChange(milkShop);
        Store trash = Trash.getInstance();
        Store warehouse = Warehouse.getInstance();
        Store shop = Shop.getInstance();
        assertThat(expected1.getData().get(0), is(trash.getData().get(0)));
        assertThat(expected2.getData().get(0), is(warehouse.getData().get(0)));
        assertThat(expected3.getData().get(0), is(shop.getData().get(0)));
        trash.clear();
        warehouse.clear();
        shop.clear();
    }
}