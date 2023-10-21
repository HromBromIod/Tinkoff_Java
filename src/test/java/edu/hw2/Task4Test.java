package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {
    @Test
    @DisplayName("Call checkThis1")
    void checkThis1() {
        Task4.CallingInfo actual = Task4.callingInfo();
        Task4.CallingInfo expected = new Task4.CallingInfo("edu.hw2.Task4Test", "checkThis1");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Call checkThis2")
    void checkThis2() {
        class CheckClass {
            CheckClass() {
            }

            public static Task4.CallingInfo call() {
                return Task4.callingInfo();
            }
        }
        CheckClass personClass = new CheckClass();
        Task4.CallingInfo actual = personClass.call();
        Task4.CallingInfo expected = new Task4.CallingInfo("edu.hw2.Task4Test$1CheckClass", "call");
        assertEquals(expected, actual);
    }
}
