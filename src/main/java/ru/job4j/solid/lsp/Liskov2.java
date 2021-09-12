package ru.job4j.solid.lsp;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Liskov2 {

    private final List<String> list = new LinkedList<>();

    public void add(String name) {
        list.add(name);
    }

    public void addAll(List<String> data) {
        list.addAll(data);
    }

    public void drop(String name) {
        list.remove(name);
    }

    public List<String> getAllData() {
        return list;
    }

    public List<String> search(Predicate<String> conditions) {
        return list.stream().filter(conditions).collect(Collectors.toList());
    }

}

class WrongExample extends Liskov2 {

    @Override
    public void add(String name) {
        super.add(name);
    }

    /** @param data - Preconditions. Was strengthened */
    @Override
    public void addAll(List<String> data) {
        if (data.size() > 10) {
            throw new IllegalArgumentException("Incorrect data size");
        }
        super.addAll(data);
    }

    @Override
    public void drop(String name) {

        super.drop(name);
    }

    /** @return null - not allowed. Post conditions were weaken  */
    @Override
    public List<String> getAllData() {
        return null;
    }

    @Override
    public List<String> search(Predicate<String> conditions) {
        return super.search(conditions);
    }
}
