package edu.project3logAnalyzer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class MetricsCollectorTest {
    @Test
    void oneLogString() {
        LocalDateTime datetime = LocalDateTime.of(1970, 1, 1, 0, 0);

        LogString string = new LogString(
            "source",
            "0.0.0.0",
            "remote user",
            datetime,
            "GET request HTTP/1.1",
            200,
            100,
            "referer",
            "http user"
        );

        MetricsCollector collector = new MetricsCollector();
        collector.collect(string);

        Metrics metrics = collector.getMetrics();

        assertThat(metrics.files()).isEqualTo(Set.of("source"));
        assertThat(metrics.from()).isEqualTo(datetime);
        assertThat(metrics.to()).isEqualTo(datetime);
        assertThat(metrics.bytes()).isEqualTo(100);
        assertThat(metrics.requestsCount()).isEqualTo(1);
        assertThat(metrics.requests()).isEqualTo(Map.of("request", 1));
        assertThat(metrics.statuses()).isEqualTo(Map.of(200, 1));
    }

    @Test
    void severalLogStrings() throws IOException {
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

        assertThat(metrics.files()).isEqualTo(Set.of("source 1", "source 2"));
        assertThat(metrics.from()).isEqualTo(datetimeFirst);
        assertThat(metrics.to()).isEqualTo(datetimeSecond);
        assertThat(metrics.bytes()).isEqualTo(101);
        assertThat(metrics.requestsCount()).isEqualTo(2);
        assertThat(metrics.requests()).isEqualTo(Map.of("request1", 1, "request2", 1));
        assertThat(metrics.statuses()).isEqualTo(Map.of(201, 1, 202, 1));
    }

    @Test
    void withoutLogs() {
        MetricsCollector collector = new MetricsCollector();

        Metrics metrics = collector.getMetrics();

        assertThat(metrics.files()).isEqualTo(Set.of());
        assertThat(metrics.from()).isNull();
        assertThat(metrics.to()).isNull();
        assertThat(metrics.bytes()).isEqualTo(0);
        assertThat(metrics.requestsCount()).isEqualTo(0);
        assertThat(metrics.requests()).isEqualTo(Map.of());
        assertThat(metrics.statuses()).isEqualTo(Map.of());
    }
}
