package edu.project3logAnalyzer;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class LogsParserTest {
    @Test
    void successRead() {
        String log = "5.83.131.103 - - [17/May/2015:08:05:57 +0000] " +
            "\"GET /downloads/product_1 HTTP/1.1\" 404 346 \"-\" " +
            "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";

        LogString logString = LogsParser.parseString(log, "test");

        assertThat(logString.source()).isEqualTo("test");
        assertThat(logString.status()).isEqualTo(404);
        assertThat(logString.bodyBytesSent()).isEqualTo(346);
        assertThat(logString.timeLocal()).isEqualTo(LocalDateTime.of(2015, 5, 17, 8, 5, 57));
        assertThat(logString.request()).isEqualTo("GET /downloads/product_1 HTTP/1.1");
        assertThat(logString.httpUser()).isEqualTo("Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)");
        assertThat(logString.remoteUser()).isEqualTo("-");
        assertThat(logString.remoteAddress()).isEqualTo("5.83.131.103");
    }

    @Test
    void wrongString() {
        String log = "why not!";

        LogString logString = LogsParser.parseString(log, "test");

        assertThat(logString).isNull();
    }
}
