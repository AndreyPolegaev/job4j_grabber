package ru.job4j.solid.isp;

/**
 * пункт меню может иметь потомка
 * при создании объекта генерируется уникальный id пункта меню
 */

public class Item {

    private String name;

    private static int generateId = 0;

    private Item child;

    private int id;

    public Item(String name) {
        this.name = name;
        this.id = generateId++;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addChild(Item child) {
        this.child = child;
    }

    public boolean isEmptyChild() {
        if (child != null) {
            return false;
        }
        return true;
    }

    public Item getChild(int separator) {
        child.setName("-".repeat(separator) + child.getName());
        return child;
    }

    @Override
    public String toString() {
        return  name;
    }
}
