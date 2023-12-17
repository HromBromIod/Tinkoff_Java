package edu.hw11;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static edu.hw11.Task1.createClassWithCustomToStringImplementation;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    private Task1Test() {
    }

    @Test
    @DisplayName("toString is \"Hello, ByteBuddy!\"")
    void runtimeToStringImplementation()
        throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String customToStringReturnValue = "Hello, ByteBuddy!";
        assertEquals(
            customToStringReturnValue,
            createClassWithCustomToStringImplementation(customToStringReturnValue)
                .toString()
        );
    }
}
