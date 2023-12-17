package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import java.lang.reflect.InvocationTargetException;

public class Task1 {
    private Task1() {
    }

    public static Object createClassWithCustomToStringImplementation(String toStringReturnValue)
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        DynamicType.Unloaded<Object> unloadedType = new ByteBuddy()
            .subclass(Object.class)
            .method(ElementMatchers.isToString())
            .intercept(FixedValue.value(toStringReturnValue))
            .make();

        Class<?> dynamicType = unloadedType.load(Task1.class.getClassLoader())
            .getLoaded();

        return dynamicType.getDeclaredConstructor().newInstance();
    }
}
