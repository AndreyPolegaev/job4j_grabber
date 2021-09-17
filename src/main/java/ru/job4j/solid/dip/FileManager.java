package ru.job4j.solid.dip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * example 1
 */
public class FileManager {

    private Writer writer = new Writer();

    public void write() {
        writer.write();
    }

}

class Writer {
    public void write() {
        System.out.println("write text...");
    }
}

// как решить: выделить интерфейс, и каждый класс по выводу (консоль, файл, базу)
// реализовать от этого интерфейса.  Так же выделить интерфейс для FileManager
// и принимать класс Writer в конструкторе.
interface OurWriter {
    void write();
}


/**
 * example 2
 */

class NewStore {

    Map<String, String> booksStore = new HashMap<>();

    public void load() throws IOException {
        Files.lines(Path.of("D:\\")).forEach(System.out::println);
    }
}

class Book {
    private String text;
    public void getText() {
        System.out.println(text);
    }

}
// решение: проинициализировать мапу в конструкторе.
// добавить интерфейсы на NewStore (на метод load())
// должна быть возможность читать данные с базы, диска, сети


/**
 * example 3
 */

class Converter {

    private LocalDate date = LocalDate.of(2021, 5, 5);

    private void generateNewDate() {
        // генерация даты
    }

    private LocalDateTime converter() {
        return LocalDateTime.now();
    }
}

// решение: принимать в конструкторе обьект LocalDate + интерфейс на Converter.
// метод converter() вынести в  отдельныйкласс + его интерфейс.

