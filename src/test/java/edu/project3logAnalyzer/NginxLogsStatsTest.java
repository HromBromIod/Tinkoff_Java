package edu.project3logAnalyzer;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Проверка, что параметры считываются
 */
public class NginxLogsStatsTest {
    private final static String LOG = "5.83.131.103 - - [17/May/2015:08:05:57 +0000] " +
        "\"GET /downloads/product_1 HTTP/1.1\" 404 346 \"-\" " +
        "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";

    /** На одну секунду меньше */
    private final static String LOG_BEFORE = "5.83.131.103 - - [17/May/2015:08:05:56 +0000] " +
        "\"GET /downloads/product_1 HTTP/1.1\" 404 346 \"-\" " +
        "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";

    /** На одну секунду больше */
    private final static String LOG_AFTER = "5.83.131.103 - - [17/May/2015:08:05:58 +0000] " +
        "\"GET /downloads/product_1 HTTP/1.1\" 404 346 \"-\" " +
        "\"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.22)\"";
    private final static String MARKDOWN_URL = "### Общая информация\n" +
        "\n" +
        "|        Метрика         |           Значение           |\n" +
        "|:----------------------:|:----------------------------:|\n" +
        "|        Файл(ы)         | [http://localhost:8080/logs] |\n" +
        "| Начальные дата и время |     17/05/2023 08:05:57      |\n" +
        "| Конечные дата и время  |     17/05/2023 08:05:57      |\n" +
        "|  Количество запросов   |              1               |\n" +
        "| Средний размер ответа  |             346              |\n" +
        "\n" +
        "### Запрашиваемые ресурсы\n" +
        "\n" +
        "|                       Ресурс                       | Количество |\n" +
        "|:--------------------------------------------------:|:----------:|\n" +
        "| /downloads/product_1 HTTP/1.1 |     1      |\n" +
        "\n" +
        "### Коды ответа\n" +
        "\n" +
        "| Статус | Имя | Количество |\n" +
        "|:------:|:---:|:----------:|\n" +
        "|  404   | OK  |     1      |\n" +
        "\n";
    private final static String MARKDOWN_FILE = "### Общая информация\n" +
        "\n" +
        "|        Метрика         |      Значение       |\n" +
        "|:----------------------:|:-------------------:|\n" +
        "|        Файл(ы)         |       [file]        |\n" +
        "| Начальные дата и время | 17/05/2023 08:05:57 |\n" +
        "| Конечные дата и время  | 17/05/2023 08:05:57 |\n" +
        "|  Количество запросов   |          1          |\n" +
        "| Средний размер ответа  |         346         |\n" +
        "\n" +
        "### Запрашиваемые ресурсы\n" +
        "\n" +
        "|                       Ресурс                       | Количество |\n" +
        "|:--------------------------------------------------:|:----------:|\n" +
        "| /downloads/product_1 HTTP/1.1 |     1      |\n" +
        "\n" +
        "### Коды ответа\n" +
        "\n" +
        "| Статус | Имя | Количество |\n" +
        "|:------:|:---:|:----------:|\n" +
        "|  404   | OK  |     1      |\n" +
        "\n";

    private final static String DIRECTORY_PATH = "test_nginx";
    private WireMockServer wireMockServer;

    @BeforeEach
    void createDirectory() throws Exception {
        deleteDirectory(new File(DIRECTORY_PATH));
        Files.createDirectory(Path.of(DIRECTORY_PATH));
    }

    @AfterEach
    void deleteAllFiles() throws Exception {
        deleteDirectory(new File(DIRECTORY_PATH));
    }

    @BeforeEach
    public void setup() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
    }

    @AfterEach
    public void tearDown() {
        wireMockServer.stop();
    }

    public static void deleteDirectory(File directory) throws Exception {
        if (!directory.exists()) {
            return;
        }

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }

        // Удаляем саму директорию
        if (!directory.delete()) {
            throw new Exception("Не удалось удалить файл");
        }
    }

    @Test
    void url() throws IOException, InterruptedException {
        stubFor(get(urlEqualTo("/logs"))
            .willReturn(aResponse()
                .withStatus(200)
                .withBody(LOG_BEFORE + "\n" + LOG + "\n" + LOG_AFTER)));

        String answer = NginxLogsStats.nginxLogsStats(List.of(
            "--path", wireMockServer.url("/logs"),
            "--from", LocalDateTime.of(2023, 11, 15, 21, 34, 33).toString(),
            "--to", LocalDateTime.of(2023, 11, 15, 21, 34, 33).toString(),
            "--format", "markdown"
        ).toArray(new String[0]));

        assertThat(answer.equals(MARKDOWN_URL));
    }

    @Test
    void file() throws IOException, InterruptedException {
        Path path = Path.of(DIRECTORY_PATH, "file");
        Files.createFile(path);
        Files.writeString(path, LOG);

        String answer = NginxLogsStats.nginxLogsStats(List.of(
            "--path", path.toString(),
            "--from", LocalDateTime.of(2023, 11, 15, 21, 34, 33).toString(),
            "--to", LocalDateTime.of(2023, 11, 15, 21, 34, 33).toString(),
            "--format", "markdown"
        ).toArray(new String[0]));

        assertThat(answer.equals(MARKDOWN_FILE));
    }

    @Test
    void withoutPath() {
        assertThrows(
            IllegalArgumentException.class,
            () -> NginxLogsStats.nginxLogsStats(new String[] {})
        );
    }
}
