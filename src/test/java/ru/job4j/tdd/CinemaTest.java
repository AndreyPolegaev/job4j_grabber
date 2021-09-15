package ru.job4j.tdd;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Ignore
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        cinema.add(new Session4D());
        cinema.add(new Session5D());
        List<Session> sessions = cinema.find(s -> true);
        assertThat(sessions, is(List.of(
                new Session3D(),
                new Session4D(),
                new Session5D()
        )));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongPlaceSameData() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Account account1 = new AccountCinema();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Account account2 = new AccountCinema();
        Calendar date2 = Calendar.getInstance();
        date2.set(2020, 10, 10, 23, 00);
        Ticket ticket1 = cinema.buy(account1, 1, 1, date);
        Ticket ticket2 = cinema.buy(account2, 1, 1, date);
        assertThat(ticket1, is(ticket2));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenWrongData() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Account account1 = new AccountCinema();
        Calendar dateOrder = Calendar.getInstance();
        dateOrder.set(2021, 9, 14, 23, 00);
        Calendar today = Calendar.getInstance();
        today.set(2021, 9, 15, 23, 00);
        Ticket ticket1 = cinema.buy(account1, 1, 1, dateOrder);
        assertNotEquals(dateOrder, today);
    }
}