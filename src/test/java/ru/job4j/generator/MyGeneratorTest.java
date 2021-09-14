package ru.job4j.generator;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import java.util.HashMap;
import java.util.Map;

public class MyGeneratorTest {

    @Test
    public void whenAllRight() {
        Generator gen = new MyGenerator();
        String produce = gen.produce("I am a ${name}, Who are ${subject}? ", new HashMap<>(Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        )));
        assertThat(produce, is("I am a Petr Arsentev, Who are you? "));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenStringDoesNotHaveKey() {
        Generator gen = new MyGenerator();
        String produce = gen.produce("I am a ${name}, Who are ${subject}? ", new HashMap<>(Map.of(
                "name", "Petr Arsentev",
                "subject", "you",
                "key", "test"
        )));
        assertThat(produce, is("I am a Petr Arsentev, Who are you? "));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void whenHashMapDoesNotHaveStringsKey() {
        Generator gen = new MyGenerator();
        String produce = gen.produce("I am a ${name}, Who are ${subject}? ${absent} ", new HashMap<>(Map.of(
                "name", "Petr Arsentev",
                "subject", "you"
        )));
        assertThat(produce, is("I am a Petr Arsentev, Who are you? "));
    }
}
