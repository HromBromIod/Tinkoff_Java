package edu.project3logAnalyzer;

import java.io.IOException;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class MetricsToStringTest {
    @Test
    void markdown() throws IOException {
        LocalDateTime datetimeFirst = LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime datetimeSecond = LocalDateTime.of(1970, 1, 1, 0, 1);

        LogString first = new LogString(
            "source 1",
            "0.0.0.1",
            "remote user 1",
            datetimeFirst,
            "GET request1 HTTP/1.1",
            201,
            100,
            "referer 1",
            "http user 1"
        );

        LogString second = new LogString(
            "source 2",
            "0.0.0.2",
            "remote user 2",
            datetimeSecond,
            "GET request2 HTTP/1.1",
            202,
            102,
            "referer 2",
            "http user 2"
        );

        MetricsCollector collector = new MetricsCollector();
        collector.collect(first);
        collector.collect(second);

        Metrics metrics = collector.getMetrics();

        assertThat(MetricsToString.markdown(metrics)
            .equals("### Общая информация\n" +
                "\n" +
                "|        Метрика         |       Значение       |\n" +
                "|:----------------------:|:--------------------:|\n" +
                "|        Файл(ы)         | [source 1, source 2] |\n" +
                "| Начальные дата и время | 01/01/1970 00:00:00  |\n" +
                "| Конечные дата и время  | 01/01/1970 00:01:00  |\n" +
                "|  Количество запросов   |          2           |\n" +
                "| Средний размер ответа  |         101          |\n" +
                "\n" +
                "### Запрашиваемые ресурсы\n" +
                "\n" +
                "|  Ресурс  | Количество |\n" +
                "|:--------:|:----------:|\n" +
                "| request2 |     1      |\n" +
                "| request1 |     1      |\n" +
                "\n" +
                "### Коды ответа\n" +
                "\n" +
                "| Статус |   Имя    | Количество |\n" +
                "|:------:|:--------:|:----------:|\n" +
                "|  201   | Created  |     1      |\n" +
                "|  202   | Accepted |     1      |\n" +
                "\n"));
    }

    @Test
    void adoc() throws IOException {
        LocalDateTime datetimeFirst = LocalDateTime.of(1970, 1, 1, 0, 0);
        LocalDateTime datetimeSecond = LocalDateTime.of(1970, 1, 1, 0, 1);

        LogString first = new LogString(
            "source 1",
            "0.0.0.1",
            "remote user 1",
            datetimeFirst,
            "GET request1 HTTP/1.1",
            201,
            100,
            "referer 1",
            "http user 1"
        );

        LogString second = new LogString(
            "source 2",
            "0.0.0.2",
            "remote user 2",
            datetimeSecond,
            "GET request2 HTTP/1.1",
            202,
            102,
            "referer 2",
            "http user 2"
        );

        MetricsCollector collector = new MetricsCollector();
        collector.collect(first);
        collector.collect(second);

        Metrics metrics = collector.getMetrics();

        assertThat(MetricsToString.adoc(metrics)
            .equals("== Общая информация\n" +
                "\n" +
                "[options=\"header\"]\n" +
                "[cols=\"1,1\"]\n" +
                "|===\n" +
                "|Метрика|Значение\n" +
                "|Файл(ы)|[source 1, source 2]\n" +
                "|Начальные дата и время|01/01/1970 00:00:00\n" +
                "|Конечные дата и время|01/01/1970 00:01:00\n" +
                "|Количество запросов|2\n" +
                "|Средний размер ответа|101\n" +
                "|===\n" +
                "\n" +
                "== Запрашиваемые ресурсы\n" +
                "\n" +
                "[options=\"header\"]\n" +
                "[cols=\"1,1\"]\n" +
                "|===\n" +
                "|Ресурс|Количество\n" +
                "|request2|1\n" +
                "|request1|1\n" +
                "|===\n" +
                "\n" +
                "== Коды ответа\n" +
                "\n" +
                "[options=\"header\"]\n" +
                "[cols=\"1,1,1\"]\n" +
                "|===\n" +
                "|Статус|Имя|Количество\n" +
                "|201|Created|1\n" +
                "|202|Accepted|1\n" +
                "|===\n" +
                "\n"));
    }

    @Test
    void markdownEmpty() throws IOException {
        MetricsCollector collector = new MetricsCollector();

        Metrics metrics = collector.getMetrics();

        assertThat(MetricsToString.markdown(metrics)
            .equals("### Общая информация\n" +
                "\n" +
                "|        Метрика         | Значение |\n" +
                "|:----------------------:|:--------:|\n" +
                "|        Файл(ы)         |    []    |\n" +
                "| Начальные дата и время |    -     |\n" +
                "| Конечные дата и время  |    -     |\n" +
                "|  Количество запросов   |    0     |\n" +
                "| Средний размер ответа  |    0     |\n" +
                "\n" +
                "### Запрашиваемые ресурсы\n" +
                "\n" +
                "| Ресурс | Количество ||:------:|:----------:|\n" +
                "\n" +
                "\n" +
                "### Коды ответа\n" +
                "\n" +
                "| Статус | Имя | Количество ||:------:|:---:|:----------:|\n" +
                "\n" +
                "\n"));
    }

    @Test
    void adocEmpty() throws IOException {
        MetricsCollector collector = new MetricsCollector();

        Metrics metrics = collector.getMetrics();

        assertThat(MetricsToString.adoc(metrics)
            .equals("== Общая информация\n" +
                "\n" +
                "[options=\"header\"]\n" +
                "[cols=\"1,1\"]\n" +
                "|===\n" +
                "|Метрика|Значение\n" +
                "|Файл(ы)|[]\n" +
                "|Начальные дата и время|-\n" +
                "|Конечные дата и время|-\n" +
                "|Количество запросов|0\n" +
                "|Средний размер ответа|0\n" +
                "|===\n" +
                "\n" +
                "== Запрашиваемые ресурсы\n" +
                "\n" +
                "[options=\"header\"]\n" +
                "[cols=\"1,1\"]\n" +
                "|===\n" +
                "|Ресурс|Количество\n" +
                "|===\n" +
                "\n" +
                "== Коды ответа\n" +
                "\n" +
                "[options=\"header\"]\n" +
                "[cols=\"1,1,1\"]\n" +
                "|===\n" +
                "|Статус|Имя|Количество\n" +
                "|===\n" +
                "\n"));
    }
}
