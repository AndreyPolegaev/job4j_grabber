package ru.job4j.kissdryyagni;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenMax() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comparator = Integer::compare;
        List<Integer> list = new ArrayList<>(List.of(-5, 10, 2));
        Integer max = maxMin.max(list, comparator);
        assertThat(max, is(10));
    }

    @Test
    public void whenMin() {
        MaxMin maxMin = new MaxMin();
        Comparator<Integer> comparator = Integer::compare;
        List<Integer> list = new ArrayList<>(List.of(-5, 10, 2));
        Integer max = maxMin.min(list, comparator);
        assertThat(max, is(-5));
    }

    @Test
    public void whenStringMax() {
        MaxMin maxMin = new MaxMin();
        Comparator<String> comparator = String::compareTo;
        List<String> list = new ArrayList<>(List.of(
                "abc", "defg"));
        String max = maxMin.max(list, comparator);
        assertThat(max, is("defg"));
    }

    @Test
    public void whenStringMin() {
        MaxMin maxMin = new MaxMin();
        Comparator<String> comparator = String::compareTo;
        List<String> list = new ArrayList<>(List.of(
                "a", "A"));
        String min = maxMin.min(list, comparator);
        assertThat(min, is("A"));
    }
}