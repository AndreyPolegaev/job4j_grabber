package ru.job4j.cache;

/**
 * Возможности класса Emulator
 * - указать кэшируемую директорию
 * - загрузить содержимое файла в кэш
 * - получить содержимое файла из кэша
 * @Autor Andrey Polegaev
 * @Version 1.0
 */

public class Emulator {

    private final DirFileCache dir;

    public Emulator(String dirFileCache) {
        dir = new DirFileCache(dirFileCache);
    }

    public void load(String key) {
        dir.load(key);
    }

    public String getData(String key) {
        return dir.get(key);
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator("D:\\");
        emulator.load("data.txt");
        String data = emulator.getData("new.txt");
        System.out.println(data);

    }
}
