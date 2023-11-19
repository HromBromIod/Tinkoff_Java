package edu.project3logAnalyzer;

import java.time.LocalDateTime;
import java.util.function.Predicate;

public class DateTimeFromFilter implements Predicate<LocalDateTime> {
    private final LocalDateTime from;

    public DateTimeFromFilter(LocalDateTime from) {
        this.from = from;
    }

    @Override
    public boolean test(LocalDateTime localDateTime) {
        return !this.from.isAfter(localDateTime);
    }
}
