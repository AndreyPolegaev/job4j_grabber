package ru.job4j.kissdryyagni;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * класс для поиска максимального и минимального элемента по критерию java.util.Comparator.
 */

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator, false);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator, true);
    }

    public <T> T find(List<T> list, Comparator<T> comparator, boolean check) {
        Iterator<T> it = list.iterator();
        T number = it.next();
        while (it.hasNext()) {
            T next = it.next();
            if ((comparator.compare(number, next) > 0) == check) {
                number = next;
            }
        }
        return number;
    }

}
