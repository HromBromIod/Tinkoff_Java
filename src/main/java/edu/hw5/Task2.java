package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Task2 {
    private Task2() {
    }

    public static List<LocalDate> getFridayThirteenDats(Integer year) throws IllegalArgumentException {
        if (year == null) {
            throw new IllegalArgumentException("Invalid year!");
        }
        List<LocalDate> result = new ArrayList<>();
        LocalDate date = LocalDate.parse(year + "-01-13");
        while (date.getYear() == year) {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                result.add(date);
            }
            date = date.plusMonths(1);
        }
        return result;
    }

    public static LocalDate nextFridayThirteen(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Invalid date!");
        }
        final int thirteen = 13;
        LocalDate nextFridayThirteenDate = date.plusDays(1);
        while (nextFridayThirteenDate.getDayOfMonth() != thirteen) {
            nextFridayThirteenDate = nextFridayThirteenDate.plusDays(1);
        }
        while (!nextFridayThirteenDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
            nextFridayThirteenDate = nextFridayThirteenDate.plusMonths(1);
        }
        return nextFridayThirteenDate;
    }
}
