package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static edu.hw7.Task1.Task1.parallelIncrement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private Task1Test() {
    }

    @RepeatedTest(10)
    @DisplayName("parallel counter increment test")
    void test() throws InterruptedException {
        assertEquals(4, parallelIncrement());
    }
}
