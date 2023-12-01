package edu.project3logAnalyzer;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class LogsReaderUrlTest {
    private final static String LOG = "5.83.131.103 - - [17/May/2015:08:05:57 +0000] " +
        "\"GET /downloads/product_1 HTTP/1.1\" 404 346 \"-\" " +
        "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";

    private WireMockServer wireMockServer;

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    @Test
    public void answerReturned() throws IOException, InterruptedException {
        stubFor(get(urlEqualTo("/logs"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody(LOG)));

        var stream = LogsReader.readLogsFromUrl(wireMockServer.url("/logs"));

        assertThat(stream.anyMatch(element -> true)).isTrue();
    }

    @Test
    public void notOkStatus() throws IOException, InterruptedException {
        stubFor(get(urlEqualTo("/logs"))
            .willReturn(aResponse()
                .withStatus(404)
                .withBody(LOG)));

        var stream = LogsReader.readLogsFromUrl(wireMockServer.url("/logs"));

        assertThat(stream.anyMatch(element -> true)).isFalse();
    }

    @Test
    public void noUrl() throws IOException, InterruptedException {
        var stream = LogsReader.readLogsFromUrl("/logs");

        assertThat(stream.anyMatch(element -> true)).isFalse();
    }
}
