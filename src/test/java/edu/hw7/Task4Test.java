package edu.hw7;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static edu.hw7.Task4.Task4.fourThreadsPI;
import static edu.hw7.Task4.Task4.oneThreadPI;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    private Task4Test() {
    }

    @DisplayName("one thread PI test 10_000_000")
    @RepeatedTest(10)
    void oneThreadPITest1() {
        double expected = Math.abs(oneThreadPI(10_000_000));
        System.out.println("one thread: " + expected);
        assertTrue(expected - Math.PI < 0.001);
    }

    @DisplayName("one thread PI test")
    @RepeatedTest(10)
    void oneThreadPITest2() {
        double expected = Math.abs(oneThreadPI(100_000_000));
        System.out.println("one thread: " + expected);
        assertTrue(expected - Math.PI < 0.001);
    }

    @DisplayName("one thread PI test 1_000_000_000")
    @RepeatedTest(10)
    void oneThreadPITest3() {
        double expected = Math.abs(oneThreadPI(1_000_000_000));
        System.out.println("one thread: " + expected);
        assertTrue(expected - Math.PI < 0.001);
    }

    @DisplayName("four threads PI test 10_000_000")
    @RepeatedTest(10)
    void fourThreadsPITest1() throws InterruptedException {
        double expected = Math.abs(fourThreadsPI(10_000_000));
        System.out.println("four threads: " + expected);
        assertTrue(expected - Math.PI < 0.001);
    }

    @DisplayName("four threads PI test 100_000_000")
    @RepeatedTest(10)
    void fourThreadsPITest2() throws InterruptedException {
        double expected = Math.abs(fourThreadsPI(100_000_000));
        System.out.println("four threads: " + expected);
        assertTrue(expected - Math.PI < 0.001);
    }

    @DisplayName("four threads PI test 1_000_000_000")
    @RepeatedTest(10)
    void fourThreadsPITest3() throws InterruptedException {
        double expected = Math.abs(fourThreadsPI(1_000_000_000));
        System.out.println("four threads: " + expected);
        assertTrue(expected - Math.PI < 0.001);
    }
}
