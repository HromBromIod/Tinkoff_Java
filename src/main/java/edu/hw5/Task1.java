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

    public static Duration averageSessionTime(List<String> baseOfDats) throws RuntimeException {
        int sessionsCount = 0;
        int fullDuration = 0;
        if (!baseOfDats.isEmpty() && baseOfDats != null) {
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
                        throw new RuntimeException("Parse error!");
                    }
                } else {
                    throw new RuntimeException("Incorrect data format!");
                }
            }
        } else {
            throw new RuntimeException("Incorrect base of dats!");
        }
        return Duration.ofSeconds((long) fullDuration / sessionsCount);
    }
}
