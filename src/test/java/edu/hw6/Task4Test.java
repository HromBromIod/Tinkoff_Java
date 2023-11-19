package edu.hw6;

import edu.hw6.Task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    private Task4Test() {
    }

    @Test
    @DisplayName("task 4 test")
    void test() {
        Task4.main(new String[]{});
        assertTrue(Files.exists(Path.of("src/main/java/edu/hw6/Task4/Brian Kernighan.txt")));
    }
}
