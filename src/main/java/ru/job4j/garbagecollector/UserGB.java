package ru.job4j.garbagecollector;

import java.util.Objects;

public class UserGB {
    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    public static void main(String[] args) {
        User user1 = new User("Andrey", 20);
        User user2  = new User("Dima", 30);
        User userEmpty = new User();
        info();
        for (int i = 0; i < 10_000; i++) {
            new User("Name-" + i, i);
        }
        System.gc();
        info();
    }

    /**
     * -XX:MaxGCPauseMillis=20
     *
     *  User user1 = new User("Andrey", 20);
     *  Заголовок 16 байт  (64 разрядная система)
     *  поле int 4 байта
     *  поле String 12 байт (6 символов)
     *  ссылка String name 8 байт (64 разрядная система)
     *  выравнивание до кратности 8: 0 байт
     *  Итого user1 = 40 байт
     *
     *  User user2  = new User("Dima", 30);
     *  Заголовок 16 байт  (64 разрядная система)
     *  поле int 4 байта
     *  ссылка String name 8 байт (64 разрядная система)
     *  поле String 8 байт (4 символа)
     *  выравнивание до кратности 8: 4 байт
     *  Итого user2 = 40 байт
     *
     *  User userEmpty = new User();
     *  Заголовок 16 байт  (64 разрядная система)
     *  поле int 4 байта
     *  ссылка String name 8 байт (64 разрядная система)
     *  выравнивание до кратности 8: 4 байт
     *  Итого user2 = 32 байта
     */
}
