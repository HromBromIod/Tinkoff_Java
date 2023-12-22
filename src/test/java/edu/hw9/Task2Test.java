package edu.hw9;

import edu.hw9.Task2.FinderDirectoryWithMoreThanThousandFiles;
import edu.hw9.Task2.FinderFilesWithSpecifiedSizeAndExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task2Test {
    private final Path tempDir = Files.createTempDirectory(
        Path.of("src/main/java/edu/hw9/Task2/Directory"),
        "dir_"
    );

    private final List<String> extensions = List.of(".txt", ".json", ".doc");

    public Task2Test() throws IOException {
    }

    private void createDir(Path tempDir, int level) throws IOException {
        if (level < 3) {
            Path tempDirectory = tempDir;
            for (int i = 0; i < 2000; ++i) {
                if (ThreadLocalRandom.current().nextInt(1000) == 999) {
                    tempDirectory = Files.createTempDirectory(tempDirectory, "dir" + i + "_");
                    level++;
                    createDir(tempDirectory, level);
                } else {
                    File.createTempFile("00" + i + "_", extensions.get(ThreadLocalRandom.current()
                        .nextInt(3)), tempDirectory.toFile()).deleteOnExit();
                }
            }
        }
    }

    @Test
    @DisplayName("Поиск директорий, в которых больше 1000 файлов")
    void test1() throws IOException {
        createDir(tempDir, 0);
        List<Path> answer = new ArrayList<>();
        FinderDirectoryWithMoreThanThousandFiles directoryWithMoreThanThousandFiles =
            new FinderDirectoryWithMoreThanThousandFiles(Path.of("src/main/java/edu/hw9/Task2/Directory"), answer);
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.invoke(directoryWithMoreThanThousandFiles);

        assertFalse(answer.isEmpty());
        forkJoinPool.close();
    }

    @Test
    @DisplayName("Поиск файлов, с заданным размером и расширением")
    void test2() throws IOException {
        createDir(tempDir, 2);
        List<Path> answer = new ArrayList<>();
        long size = 512;
        String extension = "txt";
        FinderFilesWithSpecifiedSizeAndExtension filesWithSpecifiedSizeAndExtension =
            new FinderFilesWithSpecifiedSizeAndExtension(
                size,
                extension,
                answer,
                Path.of("src/main/java/edu/hw9/Task2/Directory")
            );
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.invoke(filesWithSpecifiedSizeAndExtension);

        assertFalse(answer.isEmpty());
        forkJoinPool.close();
    }
}
