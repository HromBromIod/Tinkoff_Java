package edu.project3logAnalyzer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class LogsParser {
    private final static String IP_ADDRESS_PATTERN = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
    private final static String ANY_STRING_PATTERN = ".*?";
    private final static String STATUS_PATTERN = "[1-5]\\d{2}";
    private final static String BYTES_COUNT_PATTERN = "\\d{1,}";
    private final static String DATE_TIME_PATTERN = "dd/MMM/yyyy:HH:mm:ss xxxx";
    private static String generatedPattern = null;

    private LogsParser() {
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    private static String generateLogPattern() {
        if (generatedPattern != null) {
            return generatedPattern;
        }

        StringBuilder builder = new StringBuilder();

        builder.append("^")
            .append("(").append(IP_ADDRESS_PATTERN).append(")") // $remote_addr
            .append(" - ")
            .append("(").append(ANY_STRING_PATTERN).append(")") // $remote_user
            .append(" ")
            .append("\\[(").append(ANY_STRING_PATTERN).append(")\\]") // $time_local
            .append(" ")
            .append("\"(").append(ANY_STRING_PATTERN).append(")\"") // $request
            .append(" ")
            .append("(").append(STATUS_PATTERN).append(")") // $status
            .append(" ")
            .append("(").append(BYTES_COUNT_PATTERN).append(")") // $body_bytes_send
            .append(" ")
            .append("\"(").append(ANY_STRING_PATTERN).append(")\"") // $http_referer
            .append(" ")
            .append("\"(").append(ANY_STRING_PATTERN).append(")\"") // $http_user_agent
            .append("$");

        generatedPattern = builder.toString();

        return generatedPattern;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static LogString parseString(@NotNull String logString, String source) {
        Matcher matcher = Pattern.compile(generateLogPattern()).matcher(logString);

        if (!matcher.find()) {
            return null;
        }

        return new LogString(
            source,
            matcher.group(1),
            matcher.group(2),
            LocalDateTime.from(DateTimeFormatter
                .ofPattern(DATE_TIME_PATTERN)
                .localizedBy(Locale.ENGLISH)
                .parse(matcher.group(3))),
            matcher.group(4),
            Integer.parseInt(matcher.group(5)),
            Integer.parseInt(matcher.group(6)),
            matcher.group(7),
            matcher.group(8)
        );
    }
}
