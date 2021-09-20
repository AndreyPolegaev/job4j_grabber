package ru.job4j.solid.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * пункт меню может иметь потомка
 * при создании объекта генерируется уникальный id пункта меню
 */

public class Item implements Action {

    private String name;

    private static int generateId = 0;

    private List<Item> child = new ArrayList<>();

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
        this.child.add(child);
    }

    public List<Item> getChild() {
        return child;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void action1() {

    }

    @Override
    public void action2() {

    }
}
