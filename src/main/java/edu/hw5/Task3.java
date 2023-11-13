package edu.hw5;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    private Task3() {
    }

    private static final Pattern DATA_PATTERN = Pattern.compile("");

    public static Optional<LocalDate> parseDate(String dataStr) {
        LocalDate data = null;
        Matcher dataMatcher = DATA_PATTERN.matcher(dataStr);
        if (dataMatcher.find()) {
            if (dataStr.equals("yesterday")) {
                data = LocalDate.now().minusDays(1);
                return Optional.of(data);
            }
            if (dataStr.equals("today")) {
                data = LocalDate.now();
                return Optional.of(data);
            }
            if (dataStr.equals("tomorrow")) {
                data = LocalDate.now().plusDays(1);
                return Optional.of(data);
            }

        }
        return Optional.empty();
    }
}
