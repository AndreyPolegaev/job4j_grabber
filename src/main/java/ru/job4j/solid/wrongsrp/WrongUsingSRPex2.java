package ru.job4j.solid.wrongsrp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * We also see breaking the SRP rules. We have to separate logic with two classes.
 * @Autor Andrey Polegaev
 * @version 1.0
 */
public class WrongUsingSRPex2<K, V> {

    Map<K, V> data = new HashMap<>();

    public void put(K key, V value) {
        data.put(key, value);
    }

    public List<K> merge(List<K> data, Map<K, V> data2) throws IOException {
        /* some logic */
        return null;
    }

    public LocalDateTime parseTime() {
        /* some logic */
        return null;
    }
}
