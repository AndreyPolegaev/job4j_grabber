package ru.job4j.solid.wrongsrp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  This example demonstrate wrong using Single Responsibilities Principle.
 *  Right approach is to remove save method or filter method from the current class.
 *  @Autor Andrey Polegaev
 *  @version 1.0
 */

public class WrongUsingSRPex1 {

    private static class Person {

        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return  age;
        }

        @Override
        public String toString() {
            return String.format("Name: %s, Age: %d", name, age);
        }
    }


    public void save(List<WrongUsingSRPex1.Person> data) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter("D:\\data.txt"), true)) {
            data.forEach(out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WrongUsingSRPex1.Person> filter(List<WrongUsingSRPex1.Person> data) {
        return data.stream().filter(el -> el.getAge() > 20).collect(Collectors.toList());
    }


    public static void main(String[] args) throws IOException {
        WrongUsingSRPex1 wr = new WrongUsingSRPex1();
        List<WrongUsingSRPex1.Person> data = List.of(
                new WrongUsingSRPex1.Person("Name1", 20),
                new WrongUsingSRPex1.Person("Name1", 30),
                new WrongUsingSRPex1.Person("Name1", 40)
        );
        wr.save(wr.filter(data));
    }
}