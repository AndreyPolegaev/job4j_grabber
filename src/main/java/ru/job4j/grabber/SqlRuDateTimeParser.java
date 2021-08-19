package ru.job4j.grabber;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(
            Map.entry("янв", "01"),
            Map.entry("фев", "02"),
            Map.entry("мар", "03"),
            Map.entry("апр", "04"),
            Map.entry("май", "05"),
            Map.entry("июн", "06"),
            Map.entry("июл", "07"),
            Map.entry("авг", "08"),
            Map.entry("сен", "09"),
            Map.entry("окт", "10"),
            Map.entry("ноя", "11"),
            Map.entry("дек", "12")
    );

    @Override
    public LocalDateTime parse(String parse) {
        LocalDateTime localDateTime;
        String[] data = parse.replace(",", "").split(" ");
        if (data[0].equals("сегодня")) {
            localDateTime = LocalDateTime.of(
                    LocalDate.now(),
                    LocalTime.of(Integer.parseInt(data[1].split(":")[0]),
                    Integer.parseInt(data[1].split(":")[1])));
        } else if (data[0].equals("вчера")) {
            localDateTime =  LocalDateTime.of(
                    LocalDate.now().getYear(),
                    LocalDate.now().getMonth(),
                    (LocalDate.now().getDayOfMonth() - 1),
                    Integer.parseInt(data[1].split(":")[0]),
                    Integer.parseInt(data[1].split(":")[1])
                    );
        } else {
            localDateTime = LocalDateTime.of(
                    Integer.parseInt(data[2]),
                    Integer.parseInt(MONTHS.get(data[1])),
                    Integer.parseInt(data[0]),
                    Integer.parseInt(data[3].split(":")[0]),
                    Integer.parseInt(data[3].split(":")[1])
            );
        }
        return localDateTime;
    }
}
