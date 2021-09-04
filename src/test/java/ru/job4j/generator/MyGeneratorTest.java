package ru.job4j.generator;

import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import java.util.HashMap;
import java.util.Map;

public class MyGeneratorTest {

    @Ignore
    @Test
    public void whenSmth() {
        Generator gen = new MyGenerator();
        String produce = gen.produce("I am a ${name}, Who are ${subject}? ", new HashMap<>(Map.of(
                "name", "Petr Arsentev",
                "subjet", "you"
        )));
        assertThat(produce, is("I am a Petr Arsentev, Who are you? "));
    }
}
