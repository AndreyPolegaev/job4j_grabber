package ru.job4j.solid.foods;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * class implements the comparing between two dates and return %
 */

public class DateCompare implements ParseDatePercent {

    @Override
    public float dateCompare(LocalDate ld1, LocalDate ld2) {
        ZonedDateTime zoneDateTime1 = ld1.atStartOfDay(ZoneId.of("Europe/Moscow"));
        ZonedDateTime zoneDateTime2 = ld2.atStartOfDay(ZoneId.of("Europe/Moscow"));
        long start = zoneDateTime1.toInstant().toEpochMilli() / 1000;
        long finish = zoneDateTime2.toInstant().toEpochMilli() / 1000;
        ZonedDateTime zoneDateTim0 = LocalDate.now().atStartOfDay(ZoneId.of("Europe/Moscow"));
        long today = zoneDateTim0.toInstant().toEpochMilli() / 1000;
        float dif = (float) (today - start) / (finish - start);
        return dif * 100;
    }
}
