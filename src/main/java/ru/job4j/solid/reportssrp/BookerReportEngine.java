package ru.job4j.solid.reportssrp;

import java.util.function.Predicate;

/**
 * Booker's department requires to change salary by diapason
 */

public class BookerReportEngine implements Report {

    private Store store;

    private double diapasonSalary;

    public BookerReportEngine(Store store, double diapasonSalary) {
        this.store = store;
        this.diapasonSalary = diapasonSalary;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(employee.getSalary() * diapasonSalary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
