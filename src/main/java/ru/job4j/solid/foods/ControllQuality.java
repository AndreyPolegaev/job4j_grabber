package ru.job4j.solid.foods;


public class ControllQuality implements Control {

    private Store store;

    /**
     * exChange method takes Food then checks according to data parameters and then divides into 3 stores automatically
     * also applies discount for 75 - 100 % diapason
     */

    @Override
    public Store exChange(Food food) {
        DateCompare dc = new DateCompare();
        float percent = dc.dateCompare(food.getExpiryDate(), food.getCreateDate());
        if (percent >= 0.0 && percent < 25.0) {
            store = Warehouse.getInstance();
        } else if (percent >= 25.0 && percent < 75.0) {
            store = Shop.getInstance();
        } else if (percent >= 75.0 && percent < 100.0) {
            food.setDiscount(0.5f);
            store = Shop.getInstance();
        } else if (percent >= 100.0) {
            store = Trash.getInstance();
        }
        store.add(food);
        return store;
    }
}
