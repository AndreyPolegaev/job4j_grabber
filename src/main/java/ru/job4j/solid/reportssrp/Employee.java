package ru.job4j.solid.reportssrp;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Calendar;
import java.util.Objects;

/**
 * Employee model
 */


public class Employee {

    private String name;

    private Calendar hired;

    private Calendar fired;

    private double salary;

    public Employee(String name, Calendar hired, Calendar fired, double salary) {
        this.name = name;
        this.hired = hired;
        this.fired = fired;
        this.salary = salary;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Calendar getHired() {
        return hired;
    }

    public void setHired(Calendar hired) {
        this.hired = hired;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Calendar getFired() {
        return fired;
    }

    public void setFired(Calendar fired) {
        this.fired = fired;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0
                && Objects.equals(name, employee.name)
                && Objects.equals(hired, employee.hired)
                && Objects.equals(fired, employee.fired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hired, fired, salary);
    }
}
