package edu.hw10.Task1Test;

import java.lang.reflect.InvocationTargetException;
import edu.hw10.Task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task1Test {
    @Test
    @DisplayName("RandomObjectGenerator test")
    void randomObjectGeneratorTest()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        MyClass myClass = (MyClass) rog.nextObject(MyClass.class, "create");
        MyRecord myRecord = (MyRecord) rog.nextObject(MyRecord.class);
        assertNotNull(myClass);
        assertNotNull(myRecord);
    }

    @Test
    @DisplayName("nextObject null test")
    void nextObjectNullTest()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        MyClass myClass = (MyClass) rog.nextObject(MyClass.class, "с1");
        assertNull(myClass);
    }

    @Test
    @DisplayName("default nextObject object test")
    void defaultNextObjectObjectTest()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator rog = new RandomObjectGenerator();
        MyClass myClass = (MyClass) rog.nextObject(MyClass.class);
        assertNotNull(myClass);
        assertNull(myClass.string);
        assertNull(myClass.is);
        assertEquals(myClass.x, 0);
    }
}
