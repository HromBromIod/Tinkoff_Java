package edu.hw5;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private Task1() {
    }

    private final static int START_DATE_OF_SESSION = 1;
    private final static int START_TIME_OF_SESSION = 4;
    private final static int END_DATE_OF_SESSION = 6;
    private final static int END_TIME_OF_SESSION = 9;
    private static final Pattern DATA_PATTERN = Pattern.compile(
        "^(\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])),"
            + " (([0-1]\\d|2[0-4]):[0-5]\\d) - (\\d{4}-(0[1-9]|1[0-2])"
            + "-(0[1-9]|[1-2]\\d|3[0-1])), (([0-1]\\d|2[0-4]):[0-5]\\d)$");

    public static String averageSessionTime(List<String> baseOfDats) {
        int sessionsCount = 0;
        int fullDuration = 0;
        if (baseOfDats != null && !baseOfDats.isEmpty()) {
            for (String session : baseOfDats) {
                Matcher sessionMatcher = DATA_PATTERN.matcher(session);
                if (sessionMatcher.find()) {
                    try {
                        LocalDateTime startOfSession = LocalDateTime.of(
                            LocalDate.parse(sessionMatcher.group(START_DATE_OF_SESSION)),
                            LocalTime.parse(sessionMatcher.group(START_TIME_OF_SESSION))
                        );
                        LocalDateTime endOfSession = LocalDateTime.of(
                            LocalDate.parse(sessionMatcher.group(END_DATE_OF_SESSION)),
                            LocalTime.parse(sessionMatcher.group(END_TIME_OF_SESSION))
                        );
                        fullDuration +=
                            (int) Duration.between(startOfSession, endOfSession).getSeconds();
                        sessionsCount += 1;
                    } catch (DateTimeParseException exception) {
                        throw new IllegalArgumentException("Parse error!", exception);
                    }
                } else {
                    throw new IllegalArgumentException("Incorrect data format!");
                }
            }
        } else {
            throw new IllegalArgumentException("Incorrect base of dats!");
        }
        final int MINUTES_AT_HOUR = 60;
        long hours = Duration.ofSeconds((long) fullDuration / sessionsCount).toHours();
        long minutes = Duration.ofSeconds((long) fullDuration / sessionsCount).toMinutes() - hours * MINUTES_AT_HOUR;
        return hours + "ч " + minutes + 'м';
    }
}
