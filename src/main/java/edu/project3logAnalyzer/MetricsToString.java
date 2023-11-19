package edu.project3logAnalyzer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import net.steppschuh.markdowngenerator.table.Table;
import net.steppschuh.markdowngenerator.text.Text;

public class MetricsToString {
    private final static String STATUSES_PROPERTIES = "src/main/resources/http_statuses.properties";
    private final static String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";

    private MetricsToString() {
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static String markdown(Metrics metrics) throws IOException {
        Properties statusesProperties = new Properties();
        statusesProperties.load(new FileInputStream(STATUSES_PROPERTIES));
        String from;
        String to;

        if (metrics.from() != null) {
            from = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(metrics.from());
        } else {
            from = "-";
        }

        if (metrics.to() != null) {
            to = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(metrics.to());
        } else {
            to = "-";
        }

        Table.Builder generalInformation = new Table.Builder()
            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER)
            .addRow("Метрика", "Значение")
            .addRow("Файл(ы)", metrics.files().toString())
            .addRow("Начальные дата и время", from)
            .addRow("Конечные дата и время", to)
            .addRow("Количество запросов", metrics.requestsCount())
            .addRow("Средний размер ответа", metrics.bytes());

        Table.Builder requestedResources = new Table.Builder()
            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER)
            .addRow("Ресурс", "Количество");

        for (var request : metrics.requests().entrySet()) {
            requestedResources.addRow(request.getKey(), request.getValue());
        }

        Table.Builder statuses = new Table.Builder()
            .withAlignments(Table.ALIGN_CENTER, Table.ALIGN_CENTER)
            .addRow("Статус", "Имя", "Количество");

        for (var status : metrics.statuses().entrySet()) {
            statuses.addRow(
                status.getKey(),
                statusesProperties.getProperty(String.valueOf(status.getKey())),
                status.getValue()
            );
        }

        return new StringBuilder()
            .append(new Text("### Общая информация")).append("\n\n")
            .append(generalInformation.build()).append("\n\n")
            .append(new Text("### Запрашиваемые ресурсы")).append("\n\n")
            .append(requestedResources.build()).append("\n\n")
            .append(new Text("### Коды ответа")).append("\n\n")
            .append(statuses.build()).append("\n\n")
            .toString();
    }

    @SuppressWarnings("checkstyle:MultipleStringLiterals")
    public static String adoc(Metrics metrics) throws IOException {
        Properties statusesProperties = new Properties();
        statusesProperties.load(new FileInputStream(STATUSES_PROPERTIES));
        String from;
        String to;

        if (metrics.from() != null) {
            from = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(metrics.from());
        } else {
            from = "-";
        }

        if (metrics.to() != null) {
            to = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN).format(metrics.to());
        } else {
            to = "-";
        }

        StringBuilder generalInformation = new StringBuilder()
            .append("== Общая информация").append("\n\n")
            .append("[options=\"header\"]").append("\n")
            .append("[cols=\"1,1\"]").append("\n")
            .append("|===").append("\n")
            .append("|Метрика").append("|Значение").append("\n")
            .append("|Файл(ы)").append("|").append(metrics.files()).append("\n")
            .append("|Начальные дата и время")
            .append("|").append(from).append("\n")
            .append("|Конечные дата и время")
            .append("|").append(to).append("\n")
            .append("|Количество запросов").append("|").append(metrics.requestsCount()).append("\n")
            .append("|Средний размер ответа").append("|").append(metrics.bytes()).append("\n")
            .append("|===").append("\n\n");

        StringBuilder requestedResources = new StringBuilder()
            .append("== Запрашиваемые ресурсы").append("\n\n")
            .append("[options=\"header\"]").append("\n")
            .append("[cols=\"1,1\"]").append("\n")
            .append("|===").append("\n")
            .append("|Ресурс").append("|Количество").append("\n");

        for (var request : metrics.requests().entrySet()) {
            requestedResources
                .append("|").append(request.getKey())
                .append("|").append(request.getValue()).append("\n");
        }

        requestedResources.append("|===").append("\n\n");

        StringBuilder statuses = new StringBuilder()
            .append("== Коды ответа").append("\n\n")
            .append("[options=\"header\"]").append("\n")
            .append("[cols=\"1,1,1\"]").append("\n")
            .append("|===").append("\n")
            .append("|Статус")
            .append("|Имя")
            .append("|Количество").append("\n");

        for (var status : metrics.statuses().entrySet()) {
            statuses
                .append("|").append(status.getKey())
                .append("|").append(statusesProperties.getProperty(String.valueOf(status.getKey())))
                .append("|").append(status.getValue()).append("\n");
        }

        statuses.append("|===").append("\n\n");

        return generalInformation.append(requestedResources).append(statuses).toString();
    }
}
