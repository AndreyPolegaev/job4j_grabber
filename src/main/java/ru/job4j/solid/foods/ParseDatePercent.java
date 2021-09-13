package ru.job4j.solid.foods;

import java.time.LocalDate;

/**
 * describes comparing between two dates
 */

public interface ParseDatePercent {

    float dateCompare(LocalDate ld1, LocalDate ld2);

}
