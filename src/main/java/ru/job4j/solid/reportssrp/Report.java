package ru.job4j.solid.reportssrp;

import java.util.function.Predicate;

/**
 * generate(...) will return report like a string
 */

public interface Report {
    String generate(Predicate<Employee> filter);
}
