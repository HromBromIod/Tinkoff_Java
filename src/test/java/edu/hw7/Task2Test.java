package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigInteger;
import static edu.hw7.Task2.Task2.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private Task2Test() {
    }

    @Test
    @DisplayName("illegal argument test")
    void errorTest() throws InterruptedException {
        assertEquals(new BigInteger("-1"), factorial(-10));
    }

    @DisplayName("factorial test")
    @ParameterizedTest
    @CsvSource({
        "0, 1",
        "1, 1",
        "2, 2",
        "3, 6",
        "30, 265252859812191058636308480000000"
    })
    void factorialTest(int source, BigInteger expected) {
        assertEquals(new BigInteger(String.valueOf(expected)), factorial(source));
    }
}
