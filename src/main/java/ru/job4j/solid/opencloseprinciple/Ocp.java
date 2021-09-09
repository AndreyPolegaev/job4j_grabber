package ru.job4j.solid.opencloseprinciple;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Ocp {

    /**
     * Example 1
     */
    private static class WrongPerson {

        private final List<String> responsibilities = new ArrayList<>();
    }

    private static class RightPerson<T> {

        private final List<T> responsibilities;

        private RightPerson(List<T> responsibilities) {
            this.responsibilities = responsibilities;
        }

    }

    /**
     * Example 2
     */

    private static class WrongPerson1 {
        private String makeReport() {
            return "some report...";
        }
    }

    public interface Report<T> {
        T makeReport();
    }

    private static class RightPerson1 implements Report<String> {
        @Override
        public String makeReport() {
            return "do something...";
        }
    }

    private static class RightPerson2 implements Report<List<String>> {
        @Override
        public List<String> makeReport() {
            return new ArrayList<>();
        }
    }

    /**
     * Example 3
     * maximum flexibility
     */

    private static class WrongData {

        List<String> data = new ArrayList<>();

        private void executeOut() {
            data.forEach(System.out::println);
        }

    }

    public interface Store<T, K> {
        K executeOut(T t);
    }

    private static class RightData<T> implements Store<Predicate<String>, String> {

        List<T> list;

        public RightData(List<T> list) {
            this.list = list;
        }

        @Override
        public String executeOut(Predicate<String> stringPredicate) {
            return "some data...";
        }

        public static void main(String[] args) {
            RightData<Integer> rd = new RightData<>(List.of(1, 2, 3));
        }
    }
}
