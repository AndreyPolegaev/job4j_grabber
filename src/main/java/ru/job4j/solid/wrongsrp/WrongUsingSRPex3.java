package ru.job4j.solid.wrongsrp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * we should to separate saveDataBase() and memSave().
 * read() we can unite with any method - because it'll be common aim
 * @Autor Andrey Polegaev
 * @version 1.0
 */

public class WrongUsingSRPex3 {

    private List<String> list = new ArrayList<>();

    public List<String> read(String dir) throws IOException {
        return Files.lines(Path.of(dir)).collect(Collectors.toList());
    }

    public void saveDataBase(List<String> data) throws IOException {
        /* save in database */
    }

    public void memSave(List<String> data) throws IOException {
        list.addAll(data);
    }
}
