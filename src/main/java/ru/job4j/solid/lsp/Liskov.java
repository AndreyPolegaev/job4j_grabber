package ru.job4j.solid.lsp;

/** The following code demonstrates violating an Liskov Substitution Principle */

public class Liskov {

    private static class User {
        protected String name;
        protected int age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return String.format("was added: %s %d", name, age);
        }
    }

    private static class OurUsers extends User {

        @Override
        public void setName(String name) {
            super.setName(name);
        }

        /** @param age - Preconditions. Was strengthened */
        @Override
        public void setAge(int age) {
            if (age > 18) {
                super.setAge(age);
            }
        }

        public static void main(String[] args) {
            OurUsers ourUsers = new OurUsers();
            ourUsers.setName("Petr");
            ourUsers.setAge(15);
            System.out.println(ourUsers);
        }
    }
}


