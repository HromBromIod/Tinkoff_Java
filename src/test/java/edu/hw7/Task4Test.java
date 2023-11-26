package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static edu.hw7.Task4.Task4.fourThreadsPI;
import static edu.hw7.Task4.Task4.oneThreadPI;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    private Task4Test() {
    }

    @DisplayName("one thread PI test")
    @ParameterizedTest
    @CsvSource({
        "10_000_000",
        "100_000_000",
        "1_000_000_000",
    })
    void oneThreadPITest(long countOfIterations) {
        double expected = Math.abs(oneThreadPI(countOfIterations) - Math.PI);
        assertTrue(expected < 0.001);
    }

    @DisplayName("four threads PI test")
    @ParameterizedTest
    @CsvSource({
        "10_000_000",
        "100_000_000",
        "1_000_000_000",
    })
    void fourThreadsPITest(long countOfIterations) throws InterruptedException {
        double expected = Math.abs(fourThreadsPI(countOfIterations) - Math.PI);
        assertTrue(expected < 0.001);
    }
}
