package ru.job4j.solid.reportssrp;

import java.util.List;
import java.util.function.Predicate;

/**
 * database access with filter logic
 */

public interface Store {

    List<Employee> findBy(Predicate<Employee> filter);
}
