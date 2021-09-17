package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    /**
     * Мапа для работы с Soft ссылками реализация структуры кэш
     */
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<V>(value));
    }

    /**
     * Если в мапе нет значения по ключу key, вызываем load(K key) получаем данные из файла
     * и загружаем в мапу
     */

    public V get(K key) {
        if (!cache.containsKey(key)) {
            V loadFile = load(key);
            put(key, loadFile);
        }
        if (cache.get(key).get() == null) {
            V loadFile = load(key);
            put(key, loadFile);
        }
        return cache.get(key).get();
    }

    protected abstract V load(K key);
}
