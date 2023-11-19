package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {
    private Task2() {
    }

    public static boolean cloneFile(Path path) {
        boolean result = false;
        if (path != null && !path.toString().isEmpty() && Files.exists(path)) {
            Path newFilePath = createPath(path);
            try {
                Files.copy(path, newFilePath);
                result = true;

            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }
        return result;
    }

    private static Path createPath(Path path) {
        Path newPath = null;
        int countOfCopies = 1;
        while (newPath == null || Files.exists(newPath)) {
            newPath = Path.of(path.getParent().toString() + "/" + createCopyFileName(path, countOfCopies));
            countOfCopies += 1;
        }
        return newPath;
    }

    private static String createCopyFileName(Path path, int countOfCopies) {
        Path filePath = path.getFileName();
        String[] startEndOfFileName = filePath.toString().split("\\.");
        StringBuilder copyName = new StringBuilder();
        if (countOfCopies == 1) {
            copyName.append(startEndOfFileName[0]).append(" - копия").append(".").append(startEndOfFileName[1]);
        } else {
            copyName.append(startEndOfFileName[0]).append(" - копия (").append(countOfCopies).append(").")
                .append(startEndOfFileName[1]);
        }
        return copyName.toString();
    }
}
