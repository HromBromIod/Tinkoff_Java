package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static edu.hw6.Task2.Task2.cloneFile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task2Test {
    private Task2Test() {
    }

    @Test
    @DisplayName("empty path test")
    void emptyPathTest() throws IOException {
        assertFalse(cloneFile(Path.of("")));
    }

    @Test
    @DisplayName("null test")
    void nullTest() throws IOException {
        assertFalse(cloneFile(null));
    }

    @Test
    @DisplayName("first file copy test")
    void testFirstCopy() throws IOException {
        boolean actual = cloneFile(Path.of("src/main/java/edu/hw6/Task2/Tinkoff Bank Biggest Secret.txt"));
        boolean expected = Files.exists(Path.of("src/main/java/edu/hw6/Task2/Tinkoff Bank Biggest Secret - копия.txt"));
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("second file copy test")
    void testSecondCopy() throws IOException {
        boolean actual = cloneFile(Path.of("src/main/java/edu/hw6/Task2/Tinkoff Bank Secret.txt"));
        boolean expected = Files.exists(Path.of("src/main/java/edu/hw6/Task2/Tinkoff Bank Secret - копия (2).txt"));
        assertEquals(expected, actual);
    }
}
