package edu.project3logAnalyzer;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import static edu.project3logAnalyzer.LogsReader.readLogsFromFiles;
import static edu.project3logAnalyzer.LogsReader.readLogsFromUrl;

public class NginxLogsStats {
    private final static String MARKDOWN = "markdown";
    private final static String ADOC = "adoc";

    private NginxLogsStats() {}

    @SuppressWarnings({"checkstyle:UncommentedMain", "checkstyle:RegexpSinglelineJava"})
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(nginxLogsStats(args));
    }

    @SuppressWarnings({"checkstyle:InnerAssignment", "checkstyle:ModifiedControlVariable"})
    public static String nginxLogsStats(String[] args) throws IOException, InterruptedException {
        String path = null;
        LocalDateTime from = null;
        LocalDateTime to = null;
        String format = null;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--path" -> path = args[++i];
                case "--from" -> from = parseBest(args[++i]);
                case "--to" -> to = parseBest(args[++i]);
                case "--format" -> format = args[++i];
                default -> {
                }
            }
        }

        if (path == null) {
            throw new IllegalArgumentException("Параметр --path обязателен");
        }

        if (!Objects.equals(format, MARKDOWN) && !Objects.equals(format, ADOC)) {
            format = MARKDOWN;
        }

        Predicate<LocalDateTime> filter = obj -> true;

        if (from != null) {
            filter = filter.and(new DateTimeFromFilter(from));
        }

        if (to != null) {
            filter = filter.and(new DateTimeToFilter(to));
        }

        MetricsCollector collector = new MetricsCollector();
        Predicate<LocalDateTime> finalFilter = filter;

        if (isURL(path)) {
            readLogsFromUrl(path)
                .filter(logString -> finalFilter.test(logString.timeLocal()))
                .forEach(collector::collect);
        } else {
            readLogsFromFiles(path)
                .filter(logString -> finalFilter.test(logString.timeLocal()))
                .forEach(collector::collect);
        }

        return switch (format) {
            case MARKDOWN -> MetricsToString.markdown(collector.getMetrics());
            case ADOC -> MetricsToString.adoc(collector.getMetrics());
            default -> null;
        };
    }

    private static LocalDateTime parseBest(String string) {
        List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.BASIC_ISO_DATE,
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ISO_OFFSET_DATE,
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.ISO_LOCAL_TIME,
            DateTimeFormatter.ISO_OFFSET_TIME,
            DateTimeFormatter.ISO_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_OFFSET_DATE_TIME,
            DateTimeFormatter.ISO_ZONED_DATE_TIME,
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_ORDINAL_DATE,
            DateTimeFormatter.ISO_WEEK_DATE,
            DateTimeFormatter.ISO_INSTANT,
            DateTimeFormatter.RFC_1123_DATE_TIME
        );

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(string, formatter);
            } catch (Exception ignored) {
            }
        }

        return null;
    }

    private static boolean isURL(String input) {
        try {
            new URL(input);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }
}
