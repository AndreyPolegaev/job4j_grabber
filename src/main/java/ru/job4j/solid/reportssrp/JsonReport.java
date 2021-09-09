package ru.job4j.solid.reportssrp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

import java.util.function.Predicate;

public class JsonReport implements Report {

    Store store;

    public JsonReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson gson = new GsonBuilder().create();
        StringBuilder sb = new StringBuilder();
        for (Employee temp : store.findBy(filter)) {
            sb.append(gson.toJson(temp));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Employee em1 = new Employee("name1", Calendar.getInstance(), Calendar.getInstance(), 100.0);
        Employee em2 = new Employee("name2", Calendar.getInstance(), Calendar.getInstance(), 200.0);
        MemStore store = new MemStore();
        store.add(em1);
        store.add(em2);
        JsonReport js = new JsonReport(store);
        String rsl = js.generate(el -> true);
        System.out.println(rsl);
    }
}
