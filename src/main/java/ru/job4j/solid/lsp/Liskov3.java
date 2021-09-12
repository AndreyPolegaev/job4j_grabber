package ru.job4j.solid.lsp;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Liskov3 {

    private final Map<Integer, String> map = new HashMap<>();

    public void add(int number, String month) {
            map.put(number, month);
    }
}

class SomeData extends Liskov3 {

   private int value;

    /** some logic witch can cause mistakes */

    {
        value = new Random().nextInt(2);
    }

    @Override
    public void add(int value, String month) {
        if (this.value == 0) {
            throw new IllegalArgumentException("hello Im here");
        }
        super.add(value, month);
    }

    public static void main(String[] args) {
        SomeData time = new SomeData();
        time.add(11, "november");
    }
}
