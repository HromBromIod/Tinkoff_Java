package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    private Task2() {
    }

    public static List<LocalDate> getFridayThirteenDats(int year) throws IllegalArgumentException {
        List<LocalDate> result = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 13);
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
        LocalDate nextFridayThirteenDate = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        while (nextFridayThirteenDate.getDayOfMonth() != thirteen) {
            nextFridayThirteenDate = nextFridayThirteenDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }
        return nextFridayThirteenDate;
    }
}
