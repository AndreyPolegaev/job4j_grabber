package ru.job4j.solid.isp;

import java.util.List;

public class Menu {

    private final List<Item> data;

    private int separator = 0;

    public Menu(List<Item> data) {
        this.data = data;
    }

    public void show() {
        data.forEach(el -> {
            separator = 3;
            innerShow(el);
        });
    }

    public void innerShow(Item item) {
        System.out.println(item);
        if (!item.isEmptyChild()) {
            innerShow(item.getChild(separator++));
        }
    }


    public static void main(String[] args) {
        Item item1 = new Item("Задача 1.");
        Item item11 = new Item("Задача 1.1");
        Item item12 = new Item("Задача 1.1.2");
        item1.addChild(item11);
        item11.addChild(item12);
        Item item2 = new Item("Задача 2.");
        Item item21 = new Item("Задача 2.1");
        item2.addChild(item21);
        Item item3 = new Item("Задача 3.");
        List<Item> outItems = List.of(item1, item2, item3);
        Menu menu = new Menu(outItems);
        menu.show();
    }
}
