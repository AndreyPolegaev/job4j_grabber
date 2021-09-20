package ru.job4j.solid.isp;

import java.util.List;

public class Menu {

    private final List<Item> data;
    int count = 2;

    public Menu(List<Item> data) {
        this.data = data;
    }

    public void show() {
        for (var temp : data) {
            count = 2;
            System.out.println(temp);
            List<Item> data = temp.getChild();
            if (data.size() != 0) {
                show(data);
            }
        }
    }

    private void show(List<Item> list) {
        for (var el : list) {
            System.out.println("-".repeat(count++) + el);
            if (el.getChild().size() != 0) {
                show(el.getChild());
            }
        }
    }

    public Item findByName(String name) {
        Item item = null;
        for (var temp : data) {
            if (temp.getName().equals(name)) {
                item = temp;
                break;
            }
            List<Item> data = temp.getChild();
            if (data.size() != 0) {
                Item searchItem = search(data, name);
                if (searchItem != null) {
                    item = searchItem;
                }
            }
        }
        return item;
    }

    private Item search(List<Item> list, String name) {
        Item item = null;
        for (var temp : list) {
            if (temp.getName().equals(name)) {
                item = temp;
                break;
            } else if (temp.getChild().size() != 0) {
                search(temp.getChild(), name);
            }
        }
        return item;
    }

    public static void main(String[] args) {
        Item item1 = new Item("Задача 1.");
        Item item11 = new Item("Задача 1.1");
        Item item12 = new Item("Задача 1.1.2");
        item1.addChild(item11);
        item1.addChild(item12);
        item11.addChild(new Item("Задача 1.1.1"));

        Item item2 = new Item("Задача 2");
        Item item21 = new Item("Задача 2.1");
        Item item22 = new Item("Задача 2.1.2");
        Item item23 = new Item("Задача 2.1.3");
        item2.addChild(item21);
        item2.addChild(item22);
        item2.addChild(item23);

        List<Item> outItems = List.of(item1, item2);
        Menu menu = new Menu(outItems);
        menu.show();

        Item find = menu.findByName("Задача 2.1.3");
        System.out.println(find);
    }
}


