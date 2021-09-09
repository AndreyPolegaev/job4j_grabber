package ru.job4j.solid.reportssrp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * class for HR department's report
 * Need salary reverse order report without hired and fired date.
 */

public class HRReportEngine implements Report {

    private Store store;

    public HRReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        List<Employee> employees = store.findBy(filter);
        employees.sort((Comparator.comparingDouble(Employee::getSalary).reversed()));
        for (Employee employee : employees) {
            text.append(System.lineSeparator())
                    .append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";");
        }
        return text.toString();
    }
}
