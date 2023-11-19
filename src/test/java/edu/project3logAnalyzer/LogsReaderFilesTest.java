package edu.project3logAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogsReaderFilesTest {
    private final static String LOG = "75.98.236.143 - - [15/Nov/2023:21:34:33 +0000] " +
        "\"GET /Up-sized-dynamic-workforce/hybrid/monitoring.hmtl HTTP/1.1\" 200 1129 \"-\" " +
        "\"Mozilla/5.0 (Macintosh; PPC Mac OS X 10_7_8) AppleWebKit/5330 (KHTML, like Gecko)" +
        " Chrome/37.0.880.0 Mobile Safari/5330\"";

    private final static String DIRECTORY_PATH = "test_reader";

    @BeforeEach
    void createDirectory() throws Exception {
        deleteDirectory(new File(DIRECTORY_PATH));
        Files.createDirectory(Path.of(DIRECTORY_PATH));
    }

    @AfterEach
    void deleteAllFiles() throws Exception {
        deleteDirectory(new File(DIRECTORY_PATH));
    }

    public static void deleteDirectory(File directory) throws Exception {
        if(!directory.exists()) {
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
    void oneFile() throws IOException {
        Path path = Path.of(DIRECTORY_PATH, "file");
        Files.createFile(path);
        Files.writeString(path, LOG);

        var stream = LogsReader.readLogsFromFiles(path.toString());

        assertThat(stream.count() == 1);
    }

    @Test
    void severalFiles() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");
        Files.createFile(first);
        Files.writeString(first, LOG);
        Files.createFile(second);
        Files.writeString(second, LOG);

        var stream = LogsReader.readLogsFromFiles(DIRECTORY_PATH + "/*");

        assertThat(stream.count() == 2);
    }

    @Test
    void withoutFiles() {
        var stream = LogsReader.readLogsFromFiles(DIRECTORY_PATH + "/*");

        assertThat(stream.anyMatch(element -> true)).isFalse();
    }
}
