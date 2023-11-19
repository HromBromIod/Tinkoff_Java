package edu.project3logAnalyzer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static java.net.http.HttpClient.newHttpClient;

public class LogsReader {
    private final static int OK = 200;

    private LogsReader() {
    }

    public static Stream<LogString> readLogsFromUrl(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

            var response = newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != OK) {
                return Stream.empty();
            }

            return Arrays.stream(response.body().split("\n"))
                .map(string -> LogsParser.parseString(string, url));
        } catch (URISyntaxException | IOException | InterruptedException | IllegalArgumentException e) {
            return Stream.empty();
        }
    }

    public static Stream<LogString> readLogsFromFiles(String glob) {
        List<Path> matches = new ArrayList<>();
        String pattern = "glob:" + glob;
        Path current = Paths.get("");

        FileVisitor<Path> matcherVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attribs) throws IOException {
                PathMatcher matcher = FileSystems.getDefault().getPathMatcher(pattern);
                if (matcher.matches(file)) {
                    matches.add(file);
                }
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(current, matcherVisitor);

            return matches.stream().flatMap(path -> {
                try {
                    return Files.readAllLines(path).stream()
                        .map(string -> LogsParser.parseString(string, path.getFileName().toString()));
                } catch (IOException e) {
                    return Stream.empty();
                }
            });
        } catch (IOException ignored) {
            return Stream.empty();
        }
    }
}
