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

public class LogsReaderUrlTest {
    private final static String LOG = "75.98.236.143 - - [15/Nov/2023:21:34:33 +0000] " +
        "\"GET /Up-sized-dynamic-workforce/hybrid/monitoring.hmtl HTTP/1.1\" 200 1129 \"-\" " +
        "\"Mozilla/5.0 (Macintosh; PPC Mac OS X 10_7_8) AppleWebKit/5330 (KHTML, like Gecko)" +
        " Chrome/37.0.880.0 Mobile Safari/5330\"";

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
    public void answerReturned() {
        stubFor(get(urlEqualTo("/logs"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody(LOG)));

        var stream = LogsReader.readLogsFromUrl(wireMockServer.url("/logs"));

        assertThat(stream.anyMatch(element -> true)).isTrue();
    }

    @Test
    public void notOkStatus() {
        stubFor(get(urlEqualTo("/logs"))
            .willReturn(aResponse()
                .withStatus(404)
                .withBody(LOG)));

        var stream = LogsReader.readLogsFromUrl(wireMockServer.url("/logs"));

        assertThat(stream.anyMatch(element -> true)).isFalse();
    }

    @Test
    public void noUrl() {
        var stream = LogsReader.readLogsFromUrl("/logs");

        assertThat(stream.anyMatch(element -> true)).isFalse();
    }
}
