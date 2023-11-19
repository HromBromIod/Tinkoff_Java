package edu.project3logAnalyzer;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class LogsParserTest {
    @Test
    void successRead() {
        String log = "75.98.236.143 - - [15/Nov/2023:21:34:33 +0000] " +
            "\"GET /Up-sized-dynamic-workforce/hybrid/monitoring.hmtl HTTP/1.1\" 200 1129 \"-\" " +
            "\"Mozilla/5.0 (Macintosh; PPC Mac OS X 10_7_8) AppleWebKit/5330 (KHTML, like Gecko)" +
            " Chrome/37.0.880.0 Mobile Safari/5330\"";

        LogString logString = LogsParser.parseString(log, "test");

        assertThat(logString.source()).isEqualTo("test");
        assertThat(logString.status()).isEqualTo(200);
        assertThat(logString.bodyBytesSent()).isEqualTo(1129);
        assertThat(logString.timeLocal()).isEqualTo(LocalDateTime.of(2023, 11, 15, 21, 34, 33));
        assertThat(logString.request()).isEqualTo("GET /Up-sized-dynamic-workforce/hybrid/monitoring.hmtl HTTP/1.1");
        assertThat(logString.httpUser()).isEqualTo("Mozilla/5.0 (Macintosh; PPC Mac OS X 10_7_8) AppleWebKit/5330 (KHTML, like Gecko) Chrome/37.0.880.0 Mobile Safari/5330");
        assertThat(logString.remoteUser()).isEqualTo("-");
        assertThat(logString.remoteAddress()).isEqualTo("75.98.236.143");
    }

    @Test
    void wrongString() {
        String log = "why not!";

        LogString logString = LogsParser.parseString(log, "test");

        assertThat(logString).isNull();
    }
}
