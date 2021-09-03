package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Класс описывает реализацию абстр. класса AbstractCache
 * @Autor Andrey Polegaev
 * @Version 1.0
 */

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    /**
     * Метод сохраняет данные из файла в строку, загружает в кэш и возвращает строку
     */
    @Override
    protected String load(String key) {
        String data = null;
        try {
            data = Files.readString(Path.of(cachingDir + "/" + key));
            put(key, data);
        } catch (IOException e) {
            System.err.println("File was not found. Put another directory or file name");
        }
        return data;
    }

    @Override
    public void put(String key, String value) {
        super.put(key, value);
    }

    /**
     * @param key:  метод принимает ключ - имя файла
     * @return возвращает из мапы значение
     */
    @Override
    public String get(String key) {
        return super.get(key);
    }
}
