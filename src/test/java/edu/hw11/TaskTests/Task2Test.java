package edu.hw11.TaskTests;

import edu.hw11.Task2.Adder;
import edu.hw11.Task2.Multiplier;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw11.Task2.Task2.changeMethod;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2Test {
    private Task2Test() {
    }

    @Test
    @DisplayName("plus to multiply")
    void runtimeMethodDelegating()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final int A = 3;
        final int B = 10;
        assertEquals(
            Multiplier.multiply(A, B),
            ((Adder) changeMethod(
                Adder.class,
                Adder.class.getDeclaredMethod("sum", int.class, int.class),
                Multiplier.class
            )).sum(A, B)
        );
    }
}
