package edu.hw6;

import edu.hw6.Task3.Filter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static edu.hw6.Task3.Filter.regexContains;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {
    private Task3Test() {
    }

    public static final Path DIRECTORY = Path.of("src/main/java/edu/hw6/Task3/files");

    @Test
    @DisplayName("largerThan test")
    void largerThanTest() {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/Task3/files/132.txt")
        );

        DirectoryStream.Filter<Path> filter = Filter.largerThan(5);
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIRECTORY, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        assertEquals(expected, list);
    }

    @Test
    @DisplayName("magicNumber test")
    void magicNumberTest() throws FileNotFoundException {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/Task3/files/132.txt")
        );

        DirectoryStream.Filter<Path> filter = Filter.magicNumber('1', '2', '3');
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIRECTORY, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, list);
    }

    @Test
    @DisplayName("regexContains test")
    void regexContainsTest() {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/Task3/files/132.txt")
        );

        DirectoryStream.Filter<Path> filter = regexContains("132");
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIRECTORY, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, list);
    }

    @Test
    @DisplayName("API test")
    void APITest() {
        List<Path> list = new ArrayList<>();
        List<Path> expected = List.of(
            Path.of("src/main/java/edu/hw6/Task3/files/132.txt")
        );

        DirectoryStream.Filter<Path> filter = Filter
            .globMatches("(.*).txt")
            .and(regexContains("132"));
        try (DirectoryStream<Path> entries = Files.newDirectoryStream(DIRECTORY, filter)) {
            for (var entry : entries) {
                list.add(entry);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertEquals(expected, list);
    }
}
