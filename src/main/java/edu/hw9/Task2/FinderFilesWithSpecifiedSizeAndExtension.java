package edu.hw9.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinderFilesWithSpecifiedSizeAndExtension extends RecursiveTask<List<Path>> {
    private static final int HALF_OF_KB = 512;
    private final long sizeInBytes;
    private final String extension;
    private final List<Path> answer;
    private final Path currentPath;

    public FinderFilesWithSpecifiedSizeAndExtension(long sizeInBytes, String extension, List<Path> answer, Path currentPath) {
        this.sizeInBytes = sizeInBytes;
        this.extension = extension;
        this.answer = answer;
        this.currentPath = currentPath;
    }

    @Override
    protected List<Path> compute() {
        for (var currentFile : Objects.requireNonNull(currentPath.toFile().listFiles())) {
            if (currentFile.isDirectory()) {
                FinderFilesWithSpecifiedSizeAndExtension filesWithSpecifiedSizeAndExtension =
                    new FinderFilesWithSpecifiedSizeAndExtension(
                        sizeInBytes,
                        extension,
                        answer,
                        Path.of(currentFile.getAbsolutePath())
                    );
                filesWithSpecifiedSizeAndExtension.fork();
                filesWithSpecifiedSizeAndExtension.join();
            } else {
                try {
                    if (sizeInBytes - HALF_OF_KB <= Files.size(Path.of(currentFile.getAbsolutePath()))
                        && Files.size(Path.of(currentFile.getAbsolutePath())) <= sizeInBytes + HALF_OF_KB) {
                        String nameOfFile = currentFile.getName();
                        Matcher matcher = Pattern.compile("\\w+." + extension).matcher(nameOfFile);
                        if (matcher.find()) {
                            answer.add(Path.of(currentFile.getAbsolutePath()));
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        return answer;
    }
}
